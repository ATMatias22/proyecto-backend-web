package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.DAO.RoleRepository;
import com.sensor.persistence.entity.Role;
import com.sensor.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository; 

	@Override
	public List<Role> getAll() {
		return roleRepository.getAll();
	}

	@Override
	public Optional<Role> getRole(Long roleId) {
		Optional<Role> opt = roleRepository.getRole(roleId);
		return opt;
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return roleRepository.getRoleByName(name);
	}

}
