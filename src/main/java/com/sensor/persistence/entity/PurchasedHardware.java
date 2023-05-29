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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchased_hardware")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedHardware {


	@Id
	@Column(name = "id_purchased_hardware")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPurchasedHardware;

	@Column(name = "name")
	private String name;

	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "date_purchase")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="America/Argentina/Buenos_Aires")
	private Calendar datePurchase;

	
	@Column(name = "provider")
	private String provider;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "fk_user")
	private Long userId;
	
	@ManyToOne
	@JoinColumn(name="fk_user", insertable = false, updatable = false)
	private User user;
	

	@Column(insertable=false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="America/Argentina/Buenos_Aires")
	private Calendar created;

	//insertable para que la query de insert no la realice con esta columna
	@Column(insertable=false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="America/Argentina/Buenos_Aires")
	private Calendar updated;
	
	
}
