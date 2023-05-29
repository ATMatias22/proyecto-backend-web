package com.sensor.persistence.entity;

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

import lombok.Data;

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

	
	@Column(name = "created", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
	private Calendar created;

	@Column(name = "updated", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
	private Calendar updated;
	

	
}
