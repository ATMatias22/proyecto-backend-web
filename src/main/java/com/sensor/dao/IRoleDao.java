package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Role;

public interface IRoleDao {

	List<Role> getAll();

	Optional<Role> getRole(Long roleId);

	Optional<Role> getRoleByName(String name);
	
}
