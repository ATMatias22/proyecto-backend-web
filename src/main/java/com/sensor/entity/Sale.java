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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
	@Id
	@Column(name = "id_sale")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSale;

	@Column(name = "quantity", nullable = false)
	private Long quantity;

	@ManyToOne
	@JoinColumn(name="fk_user", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="fk_product", nullable = false)
	private Product product;

	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime created;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updated;

}
