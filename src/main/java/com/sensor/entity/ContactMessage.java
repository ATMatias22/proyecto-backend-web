package com.sensor.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name ="ContactMessage")
@Data
public class ContactMessage {

	@Id
	@Column(name = "id_contact_message")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactId;

	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "lastname", length = 50, nullable = false)
	private String lastname;

	@Column(name = "email", length = 150, nullable = false)
	private String email;
	
	@Column(name = "reason_for_contact", length = 200, nullable = false)
	private String reasonForContact;

	@Column(name = "message", columnDefinition = "TEXT", nullable = false)
	private String message;

	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime created;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updated;
	
}
