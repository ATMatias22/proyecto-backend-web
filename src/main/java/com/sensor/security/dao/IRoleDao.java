package com.sensor.security.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.security.enums.ERole;
import com.sensor.security.entity.Role;

public interface IRoleDao {

	List<Role> getAllRoles();

	Optional<Role> getRoleByERole(ERole role);

	void saveRole(Role role);
	
}
