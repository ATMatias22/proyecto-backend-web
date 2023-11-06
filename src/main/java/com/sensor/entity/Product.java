package com.sensor.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.sensor.security.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name ="Product")
@Data
public class Product {

	@Id
	@Column(name = "id_product")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name ="description", columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@ManyToOne
	@JoinColumn(name="fk_user", nullable = false)
	private User user;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "image")
	private String image;

	@OneToMany(mappedBy="product", fetch = FetchType.LAZY)
	private List<CartProduct> cartsProducts;

	@OneToMany(mappedBy="product", fetch = FetchType.LAZY)
	private Set<Stock> stocks;

	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime created;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private LocalDateTime updated;

}
