package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.DAO.ContactMessageRepository;
import com.sensor.dto.ContactMessageDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.ContactMessageMapper;
import com.sensor.persistence.entity.ContactMessage;
import com.sensor.service.ContactMessageService;

@Service
public class ContactMessageServiceImpl implements ContactMessageService{
	
	@Autowired
	private ContactMessageRepository contactMessageRepository;
	
	@Autowired
	private ContactMessageMapper contactMessageMapper;

	@Override
	public List<ContactMessageDTO> getAll() {
		return contactMessageRepository.getAll().stream().map((contactMessage) -> contactMessageMapper.toContactMessageDTO(contactMessage)).collect(Collectors.toList());
	}

	@Override
	public ContactMessageDTO getContactMessage(Long contactMessageId) {
		Optional<ContactMessage> opt = contactMessageRepository.getContactMessage(contactMessageId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el mensaje de contacto con id: " + contactMessageId);
		}
		return contactMessageMapper.toContactMessageDTO(opt.get());
	}

	@Override
	public void save(ContactMessageDTO contactMessageDTO) {
		contactMessageRepository.save(contactMessageMapper.toContactMessage(contactMessageDTO));		
	}

	@Override
	public void delete(Long contactMessageId) {
		Optional<ContactMessage> opt = contactMessageRepository.getContactMessage(contactMessageId);
		
		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el mensaje de contacto con id : " + contactMessageId);
		}
		contactMessageRepository.delete(contactMessageId);
	}
	

}
