package com.sensor.security.dao.implementation;

import java.util.List;
import java.util.Optional;

import com.sensor.security.dao.IRoleDao;
import com.sensor.security.enums.ERole;
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
		return  roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRoleByERole(ERole role) {
		return roleRepository.findByeRole(role);
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
	}


}
