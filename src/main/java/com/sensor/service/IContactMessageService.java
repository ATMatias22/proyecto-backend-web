package com.sensor.service;

import java.util.List;

import com.sensor.dto.contact.request.ContactMessageDTO;

public interface IContactMessageService {

	List<ContactMessageDTO> getAll();

	ContactMessageDTO getContactMessage(Long contactMessageId);

	void save(ContactMessageDTO contactMessageDTO);

	void delete(Long contactMessageId);
	
}
