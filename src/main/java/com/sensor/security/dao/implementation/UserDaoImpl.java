package com.sensor.security.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.security.dao.IUserDao;
import com.sensor.security.entity.User;
import com.sensor.security.repository.IUserRepository;

@Repository
public class UserDaoImpl implements IUserDao {

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
	public User saveUser(User user) {
		return IUserRepository.save(user);
	}

	@Override
	public Integer enableUser(String email) {
		return this.IUserRepository.enableUser(email);
	}

}
