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
	private IUserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);

	}
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Integer enableUser(String email) {
		return this.userRepository.enableUser(email);
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}

}
