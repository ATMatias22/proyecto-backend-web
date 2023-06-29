package com.sensor.service;

import java.util.List;

import com.sensor.dto.contact.request.ContactMessageDTO;

public interface IContactMessageService {

	List<ContactMessageDTO> getAllContactMessage();

	ContactMessageDTO getContactMessageById(Long contactMessageId);

	void saveContactMessage(ContactMessageDTO contactMessageDTO);

	void deleteContactMessageById(Long contactMessageId);
	
}
