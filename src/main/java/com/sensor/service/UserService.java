package com.sensor.service;

import java.util.List;

import com.sensor.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAll();

	UserDTO getUser(Long userId);

	void save(UserDTO userDTO);
	
	UserDTO getUserByEmail(String email);
}
