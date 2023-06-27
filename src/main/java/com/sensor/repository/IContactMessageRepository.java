package com.sensor.repository;

import org.springframework.data.repository.CrudRepository;

import com.sensor.entity.ContactMessage;

public interface IContactMessageRepository extends CrudRepository<ContactMessage, Long> {
	
}
