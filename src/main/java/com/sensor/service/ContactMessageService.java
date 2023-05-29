package com.sensor.service;

import java.util.List;
import java.util.Optional;

import com.sensor.dto.ContactMessageDTO;

public interface ContactMessageService {

	List<ContactMessageDTO> getAll();

	ContactMessageDTO getContactMessage(Long contactMessageId);

	void save(ContactMessageDTO contactMessageDTO);

	void delete(Long contactMessageId);
	
}
