package com.sensor.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IContactMessageDao;
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
	public List<ContactMessage> getAllContactMessage() {
		return contactMessageDao.getAllContactMessage();
	}

	@Override
	public ContactMessage getContactMessageById(Long contactMessageId) {
		return contactMessageDao.getContactMessageById(contactMessageId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el mensaje de contacto con id: " + contactMessageId));
	}

	@Override
	public void saveContactMessage(ContactMessage contactMessage) {
		contactMessageDao.saveContactMessage(contactMessage);
	}

	@Override
	public void deleteContactMessageById(Long contactMessageId) {
		this.getContactMessageById(contactMessageId);
		contactMessageDao.deleteContactMessageById(contactMessageId);
	}
	

}
