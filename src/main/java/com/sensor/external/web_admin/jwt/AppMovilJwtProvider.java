package com.sensor.external.web_admin.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

public class AppMovilJwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(AppMovilJwtProvider.class);

    @Value("${app.movil.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.issuer}")
    private String issuer;

    @Value("${app.movil.jwt.expiration-milliseconds}")
    private Long jwtExpirationInMs;

    public String generateToken() {

        Date fechaActual = new Date();
        Date fechaExpiration = new Date(fechaActual.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiration).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

    }
}
