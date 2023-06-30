package com.sensor.security.entity;

import java.time.LocalDateTime;

import javax.persistence.*;


import com.sensor.security.enums.ERole;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="role")
@Data
public class Role {
	
	@Id
	@Column(name = "id_role")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private ERole eRole;

	@CreationTimestamp
	@Column(name = "create_date", nullable = false)
	private LocalDateTime created;

	@UpdateTimestamp
	@Column(name = "update_date", nullable = false)
	private LocalDateTime updated;

}
