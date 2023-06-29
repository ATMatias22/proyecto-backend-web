package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IContactMessageDao;
import com.sensor.dto.contact.request.ContactMessageDTO;
import com.sensor.exception.GeneralException;
import com.sensor.mapper.ContactMessageMapper;
import com.sensor.entity.ContactMessage;
import com.sensor.service.IContactMessageService;

@Service
public class ContactMessageServiceImpl implements IContactMessageService {
	
	@Autowired
	private IContactMessageDao contactMessageDao;
	
	@Autowired
	private ContactMessageMapper contactMessageMapper;

	@Override
	public List<ContactMessageDTO> getAllContactMessage() {
		return contactMessageDao.getAllContactMessage().stream().map((contactMessage) -> contactMessageMapper.toContactMessageDTO(contactMessage)).collect(Collectors.toList());
	}

	@Override
	public ContactMessageDTO getContactMessageById(Long contactMessageId) {
		Optional<ContactMessage> opt = contactMessageDao.getContactMessageById(contactMessageId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el mensaje de contacto con id: " + contactMessageId);
		}
		return contactMessageMapper.toContactMessageDTO(opt.get());
	}

	@Override
	public void saveContactMessage(ContactMessageDTO contactMessageDTO) {
		contactMessageDao.saveContactMessage(contactMessageMapper.toContactMessage(contactMessageDTO));
	}

	@Override
	public void deleteContactMessageById(Long contactMessageId) {
		Optional<ContactMessage> opt = contactMessageDao.getContactMessageById(contactMessageId);
		
		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el mensaje de contacto con id : " + contactMessageId);
		}
		contactMessageDao.deleteContactMessageById(contactMessageId);
	}
	

}
