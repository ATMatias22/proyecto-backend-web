package com.sensor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sensor.persistence.entity.User;

public interface UserCrudRepository extends CrudRepository<User,Long>{
	
	public Optional<User> findByEmail(String email);

}
