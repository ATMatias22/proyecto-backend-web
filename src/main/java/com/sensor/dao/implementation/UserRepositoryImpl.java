package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IUserDao;
import com.sensor.security.entity.User;
import com.sensor.repository.IUserRepository;

@Repository
public class UserRepositoryImpl implements IUserDao {

	@Autowired
	private IUserRepository IUserRepository;
	
	@Override
	public List<User> getAll() {
		return (List<User>) IUserRepository.findAll();
	}

	@Override
	public Optional<User> getUser(Long userId) {
		return IUserRepository.findById(userId);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return IUserRepository.findByEmail(email);

	}

	@Override
	public User save(User user) {
		return IUserRepository.save(user);
	}

}
