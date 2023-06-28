package com.sensor.security.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.sensor.entity.Role;
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

	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "country")
	private String country;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "fk_user"),
			inverseJoinColumns = @JoinColumn(name = "fk_role"))
	private Set<Role> roles = new HashSet<>();

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

	private Boolean enabled = false;
	

}
