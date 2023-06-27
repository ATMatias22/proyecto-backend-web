package com.sensor.DAO;

import java.util.List;
import java.util.Optional;

import com.sensor.persistence.entity.Role;

public interface RoleRepository {

	List<Role> getAll();

	Optional<Role> getRole(Long roleId);

	Optional<Role> getRoleByName(String name);
	
}
