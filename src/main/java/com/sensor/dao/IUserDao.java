package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.security.entity.User;

public interface IUserDao {

	List<User> getAll();

	Optional<User> getUser(Long userId);

	Optional<User> getUserByEmail(String email);

	User save(User user);


}
