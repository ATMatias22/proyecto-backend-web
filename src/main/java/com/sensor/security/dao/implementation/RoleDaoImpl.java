package com.sensor.security.dao.implementation;

import java.util.List;
import java.util.Optional;

import com.sensor.security.dao.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.security.entity.Role;
import com.sensor.security.repository.IRoleRepository;


@Repository
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	public IRoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRole(Long roleId) {
		return roleRepository.findById(roleId);
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return roleRepository.findByName(name);

	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
	}


}
