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

import com.sensor.security.entity.Role;
import com.sensor.security.service.IRoleService;

@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Role>> getAllRoles() {
		return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
	}
	
	@GetMapping("/{roleId}")
	public ResponseEntity<Role> getRoleById(
			@PathVariable("roleId") Long roleId) {
		return new ResponseEntity<Role>(roleService.getRoleById(roleId), HttpStatus.OK);
	}
	
//	@GetMapping("/name/{typeUserByName}")
//	public ResponseEntity<TypeUser> getTypeUserByName(
//			@PathVariable("typeUserByName") String name) {
//		return new ResponseEntity<TypeUser>(typeUserService.getTypeUserByName(name).get(), HttpStatus.OK);
//	}
	

}
