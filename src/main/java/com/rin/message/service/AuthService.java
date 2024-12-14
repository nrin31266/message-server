package com.rin.message.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.rin.message.dto.request.IntrospectRequest;
import com.rin.message.dto.request.LoginRequest;
import com.rin.message.dto.response.IntrospectResponse;
import com.rin.message.dto.response.LoginResponse;
import com.rin.message.entity.User;
import com.rin.message.exception.AppException;
import com.rin.message.exception.ErrorCode;
import com.rin.message.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthService {
    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;
    PasswordEncoder passwordEncoder;




    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));


        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }

        return LoginResponse.builder()
                .token(generateToken(user))
                .build();
    }

    private String generateToken(User user) {
        JWTClaimsSet jwtClaimsSet= new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .issuer("message.rin")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .build();

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())){
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            });
        }
        return stringJoiner.toString();
    }

    public IntrospectResponse introspect(IntrospectRequest request) {
        String token = request.getToken();
        try {
            isTokenValid(token);
        } catch (ParseException parseException) {
            throw new AppException(ErrorCode.CAN_NOT_PARSE_TOKEN);
        } catch (JOSEException joseException){
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
        return IntrospectResponse.builder()
                .isValid(true)
                .build();
    }

    private void isTokenValid(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        boolean verified = signedJWT.verify(verifier);
        if(!verified){
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        if(expirationDate.before(new Date())){
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }
    }
}
