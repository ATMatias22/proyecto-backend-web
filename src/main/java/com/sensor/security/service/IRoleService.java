package com.sensor.security.service;

import java.util.List;

import com.sensor.security.entity.Role;

public interface IRoleService {
	
	List<Role> getAllRoles();

	Role getRole(Long roleId);

	Role getRoleByName(String name);

	void saveRole(Role role);

}
