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

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class AuthController {

	@Autowired
	private IAuthService authService;

	@Autowired
	private UserMapper userMapper;


	@PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JwtResponse> loginUser(@RequestBody @Valid LoginUserRequest loginUser) {
		String jwt = this.authService.loginUser(this.userMapper.loginUserRequestToUserEntity(loginUser));
		return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
	}

	@PostMapping(value = "/login-admin", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JwtResponse> loginAdminUser(@RequestBody @Valid LoginUserRequest loginUser) {
		String jwt = this.authService.loginAdminUser(this.userMapper.loginUserRequestToUserEntity(loginUser));
		return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
	}

	@PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> registerUser(@RequestBody @Valid NewUserRequest newUserRequest) {
		authService.registerUser(this.userMapper.newUserRequestToUserEntity(newUserRequest));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/confirm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtResponse> confirmRegisterUser(@RequestBody @Valid ConfirmRegisterUserRequest cru) {
		return new ResponseEntity<>(new JwtResponse(authService.confirmRegisterUser(cru.getToken())), HttpStatus.OK);
	}

}
