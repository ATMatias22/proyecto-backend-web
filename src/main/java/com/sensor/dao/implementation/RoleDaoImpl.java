package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import com.sensor.dao.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.entity.Role;
import com.sensor.repository.IRoleRepository;


@Repository
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	public IRoleRepository IRoleRepository;
	
	@Override
	public List<Role> getAll() {
		return (List<Role>) IRoleRepository.findAll();
	}

	@Override
	public Optional<Role> getRole(Long roleId) {
		return IRoleRepository.findById(roleId);
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return IRoleRepository.findByName(name);

	}
	

}
