package com.sensor.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sensor.exception.GeneralException;
import com.sensor.security.service.implementation.UserDetailServiceImpl;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

public class JwtTokenFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Estoy en filter");

		try {
			
			String token = getJwtFromRequest(request);

			// validamos el token
			if (token != null && jwtProvider.validateToken(token)) {

				String email = jwtProvider.getEmail(token);

				UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
			filterChain.doFilter(request, response);

		}catch (JwtException | AuthenticationException ja){
			log.info(ja.getMessage());
			resolver.resolveException(request, response, null, new JwtException("Problemas con el inicio de sesion, borre el token del encabezado e inicie sesion nuevamente"));
		}catch (GeneralException ex){
			log.info(ex.getMessage());
			resolver.resolveException(request, response, null, new GeneralException(HttpStatus.UNAUTHORIZED, "Problemas con el inicio de sesion, borre el token del encabezado e inicie sesion nuevamente"));
		}catch (Exception ex){
			log.info(ex.getMessage());
			resolver.resolveException(request, response, null, new Exception("Problemas en el servidor"));
		}


	}

	// Bearer token de acceso
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
