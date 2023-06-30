package com.sensor.security.repository;

import java.util.Optional;

import com.sensor.security.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sensor.security.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByeRole(ERole eRole);
}
