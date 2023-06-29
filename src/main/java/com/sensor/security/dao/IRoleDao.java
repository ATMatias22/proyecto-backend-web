package com.sensor.security.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.security.entity.Role;

public interface IRoleDao {

	List<Role> getAllRoles();

	Optional<Role> getRole(Long roleId);

	Optional<Role> getRoleByName(String name);

	void saveRole(Role role);
	
}
