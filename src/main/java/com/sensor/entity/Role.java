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
@Table(name="role")
@Data
public class Role {
	
	
	@Id
	@Column(name = "id_role")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	
	@Column(name="name")
	private String name;
	
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
