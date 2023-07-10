package com.sensor.service;

import java.util.List;

import com.sensor.entity.ContactMessage;

public interface IContactMessageService {

	List<ContactMessage> getAllContactMessage();

	ContactMessage getContactMessageById(Long contactMessageId);

	void saveContactMessage(ContactMessage contactMessage);

	void deleteContactMessageById(Long contactMessageId);
	
}
