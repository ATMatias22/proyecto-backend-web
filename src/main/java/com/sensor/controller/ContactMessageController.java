package com.sensor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.dto.contact.request.ContactMessageDTO;
import com.sensor.service.IContactMessageService;

@RestController
@RequestMapping("/contactmessages")
@CrossOrigin(origins = "*")
public class ContactMessageController {
	
	@Autowired
	private IContactMessageService contactMessageService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<ContactMessageDTO>> getAllContactMessage() {
		return new ResponseEntity<>(contactMessageService.getAllContactMessage(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{contactMessageId}")
	public ResponseEntity<ContactMessageDTO> getContactMessageById(
			@PathVariable("contactMessageId") Long contactMessageId) {
		return new ResponseEntity<ContactMessageDTO>(contactMessageService.getContactMessageById(contactMessageId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity saveContactMessage(@RequestBody ContactMessageDTO contactMessageDTO) {
		contactMessageService.saveContactMessage(contactMessageDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{contactMessageId}")
	public ResponseEntity deleteContactMessageById(@PathVariable("contactMessageId") Long contactMessageId) {
		contactMessageService.deleteContactMessageById(contactMessageId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	

}
