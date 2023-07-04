package com.sensor.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.sensor.security.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name ="Comment")
@Data
public class Comment {

	
	@Id
	@Column(name = "id_comment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@Column(name="message", nullable = false)
	private String message;

	@ManyToOne
	@JoinColumn(name="fk_user", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="fk_product", nullable = false)
	private Product product;
	
	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	

	
}
