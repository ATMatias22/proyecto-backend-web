package com.sensor.security.service;

import java.util.List;

import com.sensor.security.entity.User;

public interface IUserService {

	List<User> getAllUsers();

	User getUserById(Long userId);

	void saveUser(User user);

	void modifyPassword(String oldPassword, String newPassword);

	void confirmTokenPasswordChange (String token);


	void modifyData(User modifiedUser);

	void confirmTokenEmailChange(String token);

	Integer enableUser(String email);
	
	User getUserByEmail(String email);

	User getUserLoggedInByEmailInToken();

	void deleteUser(String password);

}
