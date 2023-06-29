package com.sensor.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.security.dto.user.response.RegisteredUserResponse;
import com.sensor.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.security.service.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private IUserService userSerivce;

	@Autowired
	private UserMapper userMapper;
	

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<RegisteredUserResponse>> getAllUsers() {
		List<RegisteredUserResponse> registeredUsers = userSerivce.getAllUsers().stream().map( user -> userMapper.userEntityToRegisteredUserResponse(user)).collect(Collectors.toList());
		return new ResponseEntity<>(registeredUsers, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RegisteredUserResponse> getUserById(
			@PathVariable("userId") Long userId) {
		RegisteredUserResponse registeredUser = userMapper.userEntityToRegisteredUserResponse(userSerivce.getUserById(userId));
		return new ResponseEntity<RegisteredUserResponse>(registeredUser, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/email/{email}")
	public ResponseEntity<RegisteredUserResponse> getUserLoggedInByEmailInToken() {
		RegisteredUserResponse registeredUser = userMapper.userEntityToRegisteredUserResponse(userSerivce.getUserLoggedInByEmailInToken());
		return new ResponseEntity<RegisteredUserResponse>(registeredUser, HttpStatus.OK);

	}

}
