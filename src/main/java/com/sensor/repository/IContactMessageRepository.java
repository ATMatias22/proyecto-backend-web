package com.sensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sensor.entity.ContactMessage;

public interface IContactMessageRepository extends JpaRepository<ContactMessage, Long> {
	
}
