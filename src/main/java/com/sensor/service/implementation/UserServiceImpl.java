package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IUserDao;
import com.sensor.dto.UserDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.UserMapper;
import com.sensor.entity.User;
import com.sensor.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao IUserDao;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> getAll() {
		return IUserDao.getAll().stream().map((user)-> userMapper.toUserDTO(user)).collect(Collectors.toList());
	}

	@Override
	public UserDTO getUser(Long userId) {

		Optional<User> opt = IUserDao.getUser(userId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con id : " + userId);
		}
		
		return userMapper.toUserDTO(opt.get());
	}

	@Override
	public void save(UserDTO userDTO) {

		Optional<User> opt = IUserDao.getUserByEmail(userDTO.getEmail());

		if (!opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "Ya existe usuario con email: " + userDTO.getEmail());
		}

		IUserDao.save(userMapper.toUser(userDTO));
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		Optional<User> opt = IUserDao.getUserByEmail(email);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con email : " + email);
		}
		return userMapper.toUserDTO(opt.get());
	}


}
