package com.sensor.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.sensor.exception.JWTException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			
			String token = obtenerJWTdeLaSolicitud(request);
			System.out.println("pase por aca 1");

			// validamos el token
			if (StringUtils.hasText(token) && jwtService.validarToken(token)) {
				System.out.println("pase por aca 2");

				String email = jwtService.getEmail(token);

				UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// establecemos la seguridad
				System.out.println("pase por aca 3");

				//esto hace que no pase por el entry point
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
			System.out.println("pase por aca 4");

			filterChain.doFilter(request, response);

		} catch (JWTException e) {
			System.out.println("pase por filter JWTException");
			resolver.resolveException(request, response, null, e);
		}


	}

	// Bearer token de acceso
	private String obtenerJWTdeLaSolicitud(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
