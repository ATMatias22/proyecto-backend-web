package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.ContactMessage;

public interface IContactMessageDao {

	List<ContactMessage> getAllContactMessage();

	Optional<ContactMessage> getContactMessageById(Long contactMessageId);

	void saveContactMessage(ContactMessage contactMessage);

	void deleteContactMessageById(Long contactMessageId);

}
