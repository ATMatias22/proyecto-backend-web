package com.sensor.repository;

import org.springframework.data.repository.CrudRepository;

import com.sensor.persistence.entity.ContactMessage;

public interface ContactMessageCrudRepository extends CrudRepository<ContactMessage, Long> {
	
}
