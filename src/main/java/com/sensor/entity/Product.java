package com.sensor.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


import com.sensor.security.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name ="Product")
@Data
public class Product {

	@Id
	@Column(name = "id_product")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(name = "name")
	private String name;

	@Column(name ="description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "price")
	private Long price;
	
	
	@Column(name = "fk_user")
	private Long userId;
	
	
	@ManyToOne
	@JoinColumn(name="fk_user", insertable = false, updatable = false)
	private User user;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "image")
	private String image;

	@Column(name = "created_date")
	@CreationTimestamp
	private Calendar created;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private Calendar updated;

}
