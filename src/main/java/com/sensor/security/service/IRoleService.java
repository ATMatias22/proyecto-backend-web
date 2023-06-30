package com.sensor.security.service;

import java.util.List;

import com.sensor.security.enums.ERole;
import com.sensor.security.entity.Role;

public interface IRoleService {
	
	List<Role> getAllRoles();

	Role getRoleByERole(ERole role);

	void saveRole(Role role);

}
