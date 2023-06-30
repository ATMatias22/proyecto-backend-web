package com.sensor.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.security.dto.role.response.RoleResponse;
import com.sensor.security.mapper.RoleMapper;
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

	@Autowired
	private RoleMapper roleMapper;
	
	@GetMapping("/all")
	public ResponseEntity<List<RoleResponse>> getAllRoles() {
		List<RoleResponse> roles = roleService.getAllRoles().stream().map(role -> roleMapper.rolToRolResponse(role)).collect(Collectors.toList());
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

}
