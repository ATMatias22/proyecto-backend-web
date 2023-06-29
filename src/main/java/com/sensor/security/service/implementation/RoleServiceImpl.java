package com.sensor.security.service.implementation;

import java.util.List;
import java.util.Optional;

import com.sensor.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.security.dao.IRoleDao;
import com.sensor.security.entity.Role;
import com.sensor.security.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao IRoleDao;

	@Override
	public List<Role> getAllRoles() {
		return IRoleDao.getAllRoles();
	}

	@Override
	public Role getRole(Long roleId) {
		return this.IRoleDao.getRole(roleId).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el role con id: "+ roleId));
	}

	@Override
	public Role getRoleByName(String name) {
		return this.IRoleDao.getRoleByName(name).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el role con nombre: "+ name));
	}

	@Override
	public void saveRole(Role role) {
		boolean exist = this.IRoleDao.getRoleByName(role.getName()).isPresent();

		if (exist){
			throw new GeneralException(HttpStatus.BAD_REQUEST, "Ya existe el nombre del role");
		}

		this.IRoleDao.saveRole(role);
	}

}
