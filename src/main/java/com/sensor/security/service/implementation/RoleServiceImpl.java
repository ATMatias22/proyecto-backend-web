package com.sensor.security.service.implementation;

import java.util.List;

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
	private IRoleDao roleDao;

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public Role getRoleById(Long roleId) {
		return this.roleDao.getRole(roleId).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el role con id: "+ roleId));
	}

	@Override
	public Role getRoleByName(String name) {
		return this.roleDao.getRoleByName(name).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el role con nombre: "+ name));
	}

	@Override
	public void saveRole(Role role) {
		boolean exist = this.roleDao.getRoleByName(role.getName()).isPresent();

		if (exist){
			throw new GeneralException(HttpStatus.BAD_REQUEST, "Ya existe el nombre del role");
		}

		this.roleDao.saveRole(role);
	}

}
