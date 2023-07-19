package com.sensor.security.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Cloneable{
	

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name="name", length = 50, nullable = false)
	private String name;

	@Column(name="lastname", length = 50, nullable = false)
	private String lastname;

	@Column(name="email", length = 150, nullable = false)
	private String email;

	@Column(name="country", length = 60, nullable = false)
	private String country;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "fk_user"),
			inverseJoinColumns = @JoinColumn(name = "fk_role"))
	private Set<Role> roles = new HashSet<>();

	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

	@Column(name="password", length = 75, nullable = false)
	private String password;


	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime created;

	@UpdateTimestamp
	@Column(name = "update_date")
	private LocalDateTime updated;

	private Boolean enabled = false;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	

}
