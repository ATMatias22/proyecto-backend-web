package com.sensor.entity;

import java.time.LocalDate;
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
@Table(name = "PurchasedHardware")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedHardware {


	@Id
	@Column(name = "id_purchased_hardware")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPurchasedHardware;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "date_purchase", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate datePurchase;

	@Column(name = "provider", nullable = false)
	private String provider;
	
	@Column(name = "price", nullable = false)
	private Long price;

	@ManyToOne
	@JoinColumn(name="fk_user", nullable = false)
	private User user;

	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime created;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updated;

	
	
}
