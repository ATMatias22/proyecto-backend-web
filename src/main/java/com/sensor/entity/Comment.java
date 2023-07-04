package com.sensor.entity;

import java.time.LocalDateTime;
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
@Table(name ="comment")
@Data
public class Comment {

	
	@Id
	@Column(name = "id_comment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	
	
	@Column(name="comment")
	private String comment;
	
	@Column(name = "fk_user")
	private Long userId;
	
	@ManyToOne
	@JoinColumn(name="fk_user", insertable = false, updatable = false)
	private User user;
	
	@Column(name = "fk_product")
	private Long productId;

	
	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	

	
}
