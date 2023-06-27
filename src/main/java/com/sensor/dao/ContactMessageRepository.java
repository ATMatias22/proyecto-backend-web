package com.sensor.DAO;

import java.util.List;
import java.util.Optional;

import com.sensor.persistence.entity.ContactMessage;
import com.sensor.persistence.entity.Product;

public interface ContactMessageRepository {

	List<ContactMessage> getAll();

	Optional<ContactMessage> getContactMessage(Long contactMessageId);

	ContactMessage save(ContactMessage contactMessage);

	void delete(Long contactMessageId);

}
