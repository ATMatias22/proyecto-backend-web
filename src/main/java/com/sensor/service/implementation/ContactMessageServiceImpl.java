package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IContactMessageDao;
import com.sensor.dto.contact.request.ContactMessageDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.ContactMessageMapper;
import com.sensor.entity.ContactMessage;
import com.sensor.service.IContactMessageService;

@Service
public class ContactMessageServiceImpl implements IContactMessageService {
	
	@Autowired
	private IContactMessageDao IContactMessageDao;
	
	@Autowired
	private ContactMessageMapper contactMessageMapper;

	@Override
	public List<ContactMessageDTO> getAll() {
		return IContactMessageDao.getAll().stream().map((contactMessage) -> contactMessageMapper.toContactMessageDTO(contactMessage)).collect(Collectors.toList());
	}

	@Override
	public ContactMessageDTO getContactMessage(Long contactMessageId) {
		Optional<ContactMessage> opt = IContactMessageDao.getContactMessage(contactMessageId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el mensaje de contacto con id: " + contactMessageId);
		}
		return contactMessageMapper.toContactMessageDTO(opt.get());
	}

	@Override
	public void save(ContactMessageDTO contactMessageDTO) {
		IContactMessageDao.save(contactMessageMapper.toContactMessage(contactMessageDTO));
	}

	@Override
	public void delete(Long contactMessageId) {
		Optional<ContactMessage> opt = IContactMessageDao.getContactMessage(contactMessageId);
		
		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el mensaje de contacto con id : " + contactMessageId);
		}
		IContactMessageDao.delete(contactMessageId);
	}
	

}
