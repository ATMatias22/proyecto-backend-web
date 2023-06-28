package com.sensor.security.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtProvider {

	private static final String EMAIL_CLAIM = "email";
	private static final String ROLES_CLAIM = "roles";

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);


	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt.issuer}")
	private String issuer;

	@Value("${app.jwt.expiration-milliseconds}")
	private Long jwtExpirationInMs;

	public String createToken(Authentication authentication) {
		String username = authentication.getName();
		List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		
		Date fechaActual = new Date();

		Date fechaExpiration = new Date(fechaActual.getTime() + jwtExpirationInMs);

		String token = Jwts.builder().setIssuer(issuer).claim(EMAIL_CLAIM,username)
				.claim(ROLES_CLAIM,roles)
				.setIssuedAt(new Date())
				.setExpiration(fechaExpiration).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;

	}

	public String getEmail(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.get(EMAIL_CLAIM).toString();
	}

	public boolean validateToken(String token) throws JwtException{
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			//Firma JWT no valida
			logger.error("firma no valida");
			throw new JwtException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente, firma no valida");

		} catch (MalformedJwtException ex) {
			//Token JWT no valida
			logger.error("Token mal formado");
			throw new JwtException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente, Token mal formado");

		} catch (ExpiredJwtException ex) {
			//expiro el JWT
			logger.error("Token expirado");
			throw new JwtException("Se expiro el inicio de sesion, por favor inicie sesion nuevamente, Token expirado");

		} catch (UnsupportedJwtException ex) {
			//Token JWT no compatible
			logger.error("Token no soportado");
			throw new JwtException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente, Token no soportado");

		} catch (IllegalArgumentException ex) {
			//La cadena claims JWT esta vacia
			logger.error("Token vacio");
			throw new JwtException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente, Token vacio");

		}
	}

}
