package com.sensor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.dto.UserDTO;
import com.sensor.service.IUserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private IUserService userSerivce;
	

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> getAll() {
		return new ResponseEntity<>(userSerivce.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> getUser(
			@PathVariable("userId") Long userId) {
		return new ResponseEntity<UserDTO>(userSerivce.getUser(userId), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated() and #email == authentication.principal.username")
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDTO> getUserByEmail(
			@PathVariable("email") String email) {
		return new ResponseEntity<UserDTO>(userSerivce.getUserByEmail(email), HttpStatus.OK);
	}

}
