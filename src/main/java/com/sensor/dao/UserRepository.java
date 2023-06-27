package com.sensor.DAO;

import java.util.List;
import java.util.Optional;

import com.sensor.persistence.entity.User;

public interface UserRepository {

	List<User> getAll();

	Optional<User> getUser(Long userId);

	Optional<User> getUserByEmail(String email);

	User save(User user);


}
