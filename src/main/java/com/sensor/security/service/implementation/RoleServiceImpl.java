package com.sensor.security.service.implementation;

import java.util.List;

import com.sensor.exception.GeneralException;
import com.sensor.security.enums.ERole;
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
	public Role getRoleByERole(ERole role) {
		return this.roleDao.getRoleByERole(role).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el role con nombre: "+ role.toString()));
	}

	@Override
	public void saveRole(Role role) {
		boolean exist = this.roleDao.getRoleByERole(role.getERole()).isPresent();

		if (exist){
			throw new GeneralException(HttpStatus.BAD_REQUEST, "Ya existe el nombre del role");
		}

		this.roleDao.saveRole(role);
	}

}
