package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IContactMessageDao;
import com.sensor.entity.ContactMessage;
import com.sensor.repository.IContactMessageRepository;

@Repository
public class ContactMessageDaoImpl implements IContactMessageDao {
	
	@Autowired
	private IContactMessageRepository contactMessageRepository;

	@Override
	public List<ContactMessage> getAllContactMessage() {
		return (List<ContactMessage>) contactMessageRepository.findAll();
	}

	@Override
	public Optional<ContactMessage> getContactMessageById(Long contactMessageId) {
		return contactMessageRepository.findById(contactMessageId);
	}

	@Override
	public ContactMessage saveContactMessage(ContactMessage contactMessage) {
		return contactMessageRepository.save(contactMessage);
	}

	@Override
	public void deleteContactMessageById(Long contactMessageId) {
		contactMessageRepository.deleteById(contactMessageId);
		
	}

}
