package com.sensor.security.service.implementation;

import java.util.List;

import com.sensor.exception.GeneralException;
import com.sensor.security.MainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sensor.security.dao.IUserDao;
import com.sensor.security.entity.User;
import com.sensor.security.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userDao.getAll();
	}

	@Override
	public User getUserById(Long userId) {
		return  this.userDao.getUser(userId).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro al usuario con id: "+userId));

	}

	@Override
	public void saveUser(User user) {
		userDao.getUserByEmail(user.getEmail()).ifPresent(u -> {
			if(!u.getEnabled()){
				throw new GeneralException(HttpStatus.BAD_REQUEST,  u.getEmail() + " ya fue registrado verifique su casilla de mails para confirmar");
			}
			throw new GeneralException(HttpStatus.BAD_REQUEST, "El email " + u.getEmail() + " ya existe");
		});
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveUser(user);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el usuario con email: " + email));
	}

	@Override
	public User getUserLoggedInByEmailInToken() {
		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return  this.userDao.getUser(mu.getId()).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro al usuario logueado por favor inicie sesion de vuelta"));
	}

	@Override
	public Integer enableUser(String email) {
		return userDao.enableUser(email);
	}


}
