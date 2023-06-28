package com.sensor.security.controller;

import com.sensor.security.dto.user.request.ConfirmRegisterUserRequest;
import com.sensor.security.dto.user.request.LoginUserRequest;
import com.sensor.security.jwt.dto.JwtResponse;
import com.sensor.security.mapper.UserMapper;
import com.sensor.security.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.security.dto.user.request.NewUserRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class AuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private UserMapper userMapper;


	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginUserRequest loginUser) {
		String jwt = this.authService.login(this.userMapper.loginUserRequestToUserEntity(loginUser));
		return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
	}

	@PostMapping("/login-admin")
	public ResponseEntity<JwtResponse> loginAdmin(@RequestBody LoginUserRequest loginUser) {
		String jwt = this.authService.login(this.userMapper.loginUserRequestToUserEntity(loginUser));
		return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody NewUserRequest newUserRequest) {
		authService.register(this.userMapper.newUserRequestToUserEntity(newUserRequest));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/confirm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtResponse> confirm(@RequestBody ConfirmRegisterUserRequest cru) {
		return new ResponseEntity<>(new JwtResponse(authService.confirmToken(cru.getToken())), HttpStatus.OK);
	}

}
