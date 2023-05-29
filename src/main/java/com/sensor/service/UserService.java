package com.sensor.service;

import java.util.List;
import java.util.Optional;

import com.sensor.dto.UserDTO;
import com.sensor.persistence.entity.User;

public interface UserService {

	List<UserDTO> getAll();

	UserDTO getUser(Long userId);

	void save(UserDTO userDTO);
	
	UserDTO getUserByEmail(String email);
}
