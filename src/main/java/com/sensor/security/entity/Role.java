package com.sensor.security.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="role")
@Data
public class Role {
	
	
	@Id
	@Column(name = "id_role")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	
	@Column(name="name")
	private String name;

	@CreationTimestamp
	@Column(name = "create_date", nullable = false)
	private Calendar created;

	@CreationTimestamp
	@Column(name = "update_date", nullable = false)
	private Calendar updated;

}
