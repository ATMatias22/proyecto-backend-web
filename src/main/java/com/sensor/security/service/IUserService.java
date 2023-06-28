package com.sensor.security.service;

import java.util.List;

import com.sensor.dto.UserDTO;
import com.sensor.security.entity.User;

public interface IUserService {

	List<UserDTO> getAll();

	UserDTO getUser(Long userId);

	void save(UserDTO userDTO);
	
	User getUserByEmail(String email);
}
