package com.sensor.entity;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name ="contact_message")
@Data
public class ContactMessage {

	@Id
	@Column(name = "id_contact_message")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactId;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "reason_for_contact")
	private String reasonForContact;
	
	@Column(name = "message")
	private String message;
	
	
	
	@Column(name = "created", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
	private Calendar created;
	
}
