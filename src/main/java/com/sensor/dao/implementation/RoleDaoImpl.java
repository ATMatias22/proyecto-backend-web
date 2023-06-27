package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import com.sensor.dao.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.persistence.entity.Role;
import com.sensor.repository.RoleCrudRepository;


@Repository
public class RoleDaoImpl implements IRoleDao {

	@Autowired
	public RoleCrudRepository roleCrudRepository;
	
	@Override
	public List<Role> getAll() {
		return (List<Role>) roleCrudRepository.findAll();
	}

	@Override
	public Optional<Role> getRole(Long roleId) {
		return roleCrudRepository.findById(roleId);
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return roleCrudRepository.findByName(name);

	}
	

}
