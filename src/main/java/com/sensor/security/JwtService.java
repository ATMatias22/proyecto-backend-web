package com.sensor.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.sensor.exception.BlogAppException;
import com.sensor.exception.JWTException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtService {

	private static final String EMAIL_CLAIM = "email";
	private static final String ROLES_CLAIM = "roles";

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

	public boolean validarToken(String token) throws JWTException{
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			//Firma JWT no valida
			throw new JWTException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente");
		} catch (MalformedJwtException ex) {
			//Token JWT no valida
			throw new JWTException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente");
		} catch (ExpiredJwtException ex) {
			//expiro el JWT
			throw new JWTException("Se expiro el inicio de sesion, por favor inicie sesion nuevamente");
		} catch (UnsupportedJwtException ex) {
			//Token JWT no compatible
			throw new JWTException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente");
		} catch (IllegalArgumentException ex) {
			//La cadena claims JWT esta vacia
			throw new JWTException("Problemas con el inicio de sesion, por favor inicie sesion nuevamente");
		}
	}

}
