package com.sensor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sensor.security.entity.User;

public interface IUserRepository extends CrudRepository<User,Long>{
	
	public Optional<User> findByEmail(String email);

}
