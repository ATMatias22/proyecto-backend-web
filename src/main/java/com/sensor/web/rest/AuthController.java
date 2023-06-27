package com.sensor.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.dto.LoginDTO;
import com.sensor.dto.UserDTO;
import com.sensor.security.JWTAuthResponseDTO;
import com.sensor.security.JwtService;
import com.sensor.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/login")
	public ResponseEntity<UserDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		
		System.out.println(authentication);

		String role = authentication.getAuthorities().toArray()[0].toString();

		if (role.equalsIgnoreCase("ROLE_USER")) {
			
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = jwtService.createToken(authentication);

			UserDTO userDTO = userService.getUserByEmail(loginDTO.getEmail());
			userDTO.setJwt(new JWTAuthResponseDTO(token));

			return ResponseEntity.ok(userDTO);

		} else {
			throw new BadCredentialsException("Algunas de sus credenciales son incorrectas");
		}
	}

	@PostMapping("/login-admin")
	public ResponseEntity<UserDTO> authenticateUserAdmin(@RequestBody LoginDTO loginDTO) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		String role = authentication.getAuthorities().toArray()[0].toString();

		if (role.equalsIgnoreCase("ROLE_ADMIN")) {
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = jwtService.createToken(authentication);

			UserDTO userDTO = userService.getUserByEmail(loginDTO.getEmail());
			userDTO.setJwt(new JWTAuthResponseDTO(token));

			return ResponseEntity.ok(userDTO);
		} else {
			throw new BadCredentialsException("Algunas de sus credenciales son incorrectas");
		}

	}

	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody UserDTO userDTO) {

		userService.save(userDTO);

		return new ResponseEntity(HttpStatus.CREATED);

	}

}
