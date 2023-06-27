package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.dao.IRoleDao;
import com.sensor.entity.Role;
import com.sensor.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private IRoleDao IRoleDao;

	@Override
	public List<Role> getAll() {
		return IRoleDao.getAll();
	}

	@Override
	public Optional<Role> getRole(Long roleId) {
		Optional<Role> opt = IRoleDao.getRole(roleId);
		return opt;
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return IRoleDao.getRoleByName(name);
	}

}
