package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IContactMessageDao;
import com.sensor.entity.ContactMessage;
import com.sensor.repository.ContactMessageCrudRepository;

@Repository
public class ContactMessageDaoImpl implements IContactMessageDao {
	
	@Autowired
	private ContactMessageCrudRepository contactMessageCrudRepository;

	@Override
	public List<ContactMessage> getAll() {
		return (List<ContactMessage>) contactMessageCrudRepository.findAll();
	}

	@Override
	public Optional<ContactMessage> getContactMessage(Long contactMessageId) {
		return contactMessageCrudRepository.findById(contactMessageId);
	}

	@Override
	public ContactMessage save(ContactMessage contactMessage) {
		return contactMessageCrudRepository.save(contactMessage);
	}

	@Override
	public void delete(Long contactMessageId) {
		contactMessageCrudRepository.deleteById(contactMessageId);
		
	}

}
