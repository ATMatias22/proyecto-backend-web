package com.sensor.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.dto.contact.request.ContactMessageRequest;
import com.sensor.dto.contact.response.ContactMessageResponse;
import com.sensor.mapper.ContactMessageMapper;
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

import com.sensor.service.IContactMessageService;

@RestController
@RequestMapping("/contact-messages")
@CrossOrigin(origins = "*")
public class ContactMessageController {
	
	@Autowired
	private IContactMessageService contactMessageService;

	@Autowired
	private ContactMessageMapper contactMessageMapper;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<ContactMessageResponse>> getAllContactMessage() {
		return new ResponseEntity<>(contactMessageService.getAllContactMessage().stream().map( cm -> this.contactMessageMapper.toContactMessageResponse(cm)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{contactMessageId}")
	public ResponseEntity<ContactMessageResponse> getContactMessageById(
			@PathVariable("contactMessageId") Long contactMessageId) {
		return new ResponseEntity<>(this.contactMessageMapper.toContactMessageResponse(contactMessageService.getContactMessageById(contactMessageId)), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> saveContactMessage(@RequestBody ContactMessageRequest contactMessageRequest) {
		contactMessageService.saveContactMessage(this.contactMessageMapper.toContactMessage(contactMessageRequest));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{contactMessageId}")
	public ResponseEntity<Void> deleteContactMessageById(@PathVariable("contactMessageId") Long contactMessageId) {
		contactMessageService.deleteContactMessageById(contactMessageId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
