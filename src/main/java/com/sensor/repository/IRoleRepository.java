package com.sensor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sensor.entity.Role;

public interface IRoleRepository extends CrudRepository<Role, Long> {

	public Optional<Role> findByName(String name);
}
