package com.sensor.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.entity.Role;
import com.sensor.security.service.IRoleService;

@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	
	@Autowired
	private IRoleService typeUserService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Role>> getAll() {
		return new ResponseEntity<>(typeUserService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{typeUserId}")
	public ResponseEntity<Role> getTypeUser(
			@PathVariable("typeUserId") Long typeUserId) {
		return new ResponseEntity<Role>(typeUserService.getRole(typeUserId).get(), HttpStatus.OK);
	}
	
//	@GetMapping("/name/{typeUserByName}")
//	public ResponseEntity<TypeUser> getTypeUserByName(
//			@PathVariable("typeUserByName") String name) {
//		return new ResponseEntity<TypeUser>(typeUserService.getTypeUserByName(name).get(), HttpStatus.OK);
//	}
	

}
