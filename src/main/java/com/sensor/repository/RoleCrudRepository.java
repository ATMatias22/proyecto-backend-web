package com.sensor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sensor.persistence.entity.Role;

public interface RoleCrudRepository extends CrudRepository<Role, Long> {

	public Optional<Role> findByName(String name);
}
