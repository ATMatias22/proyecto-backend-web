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
	private IContactMessageRepository IContactMessageRepository;

	@Override
	public List<ContactMessage> getAll() {
		return (List<ContactMessage>) IContactMessageRepository.findAll();
	}

	@Override
	public Optional<ContactMessage> getContactMessage(Long contactMessageId) {
		return IContactMessageRepository.findById(contactMessageId);
	}

	@Override
	public ContactMessage save(ContactMessage contactMessage) {
		return IContactMessageRepository.save(contactMessage);
	}

	@Override
	public void delete(Long contactMessageId) {
		IContactMessageRepository.deleteById(contactMessageId);
		
	}

}
