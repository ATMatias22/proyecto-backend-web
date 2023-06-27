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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
	@Id
	@Column(name = "id_sale")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSale;

	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "fk_user")
	private Long userId;
	
	@ManyToOne
	@JoinColumn(name="fk_user", insertable = false, updatable = false)
	private User user;
	
	
	@Column(name = "fk_product")
	private Long productId;
	
	@ManyToOne
	@JoinColumn(name="fk_product", insertable = false, updatable = false)
	private Product product;
	

	@Column(insertable=false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="America/Argentina/Buenos_Aires")
	private Calendar created;

	//insertable para que la query de insert no la realice con esta columna
	@Column(insertable=false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="America/Argentina/Buenos_Aires")
	private Calendar updated;
	
	

}
