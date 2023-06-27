package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IUserDao;
import com.sensor.persistence.entity.User;
import com.sensor.repository.UserCrudRepository;

@Repository
public class UserRepositoryImpl implements IUserDao {

	@Autowired
	private UserCrudRepository userCrudRepository;
	
	@Override
	public List<User> getAll() {
		return (List<User>) userCrudRepository.findAll();
	}

	@Override
	public Optional<User> getUser(Long userId) {
		return userCrudRepository.findById(userId);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userCrudRepository.findByEmail(email);

	}

	@Override
	public User save(User user) {
		return userCrudRepository.save(user);
	}

}
