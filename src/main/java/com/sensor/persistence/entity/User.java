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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "fk_role")
	private Long roleId;
	
	@ManyToOne
	@JoinColumn(name="fk_role", insertable = false, updatable = false)
	private Role role;
		
	
	@Column(name = "dates_birth")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd",timezone="America/Argentina/Buenos_Aires")
	private Calendar datesBirth;
	
	@Column(name = "password")
	private String password;
	

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
