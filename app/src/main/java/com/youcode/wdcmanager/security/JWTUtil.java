package com.youcode.wdcmanager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expire}")
    private long EXPIRATION_TIME;
    private final String ISSUER = "WDCManager";

    public String createToken(String id){
        return JWT.create()
                .withClaim("id", id)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + Duration.ofMinutes(EXPIRATION_TIME).toMillis()))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public DecodedJWT verifyToken(String token){
        return JWT.require(Algorithm.HMAC256(SECRET))
                .withIssuer(ISSUER)
                .acceptExpiresAt(0)
                .build()
                .verify(token);
    }

    public Instant getTokenIssuedAt(String token){
        return JWT.decode(token).getIssuedAt().toInstant();
    }

    public Claim getIdClaim(String token){
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getClaim("id");
    }


}
