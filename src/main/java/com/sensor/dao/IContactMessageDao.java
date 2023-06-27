package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.persistence.entity.ContactMessage;

public interface IContactMessageDao {

	List<ContactMessage> getAll();

	Optional<ContactMessage> getContactMessage(Long contactMessageId);

	ContactMessage save(ContactMessage contactMessage);

	void delete(Long contactMessageId);

}
