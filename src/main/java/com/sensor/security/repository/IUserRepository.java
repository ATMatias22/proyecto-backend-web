package com.sensor.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sensor.security.entity.User;

public interface IUserRepository extends CrudRepository<User,Long>{
	
	Optional<User> findByEmail(String email);

	@Modifying
	@Query("UPDATE User u " +
			"SET u.enabled = TRUE WHERE u.email = ?1")
	Integer enableUser(String email);

}
