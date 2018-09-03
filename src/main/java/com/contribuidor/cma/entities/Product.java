package com.contribuidor.cma.entities;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@Table
@Entity(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;

	private String description;

	@NotNull
	private BigDecimal price;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_category")
	private Category category;

	@NotNull
	private String storeId;

	@NotNull
	private Boolean active;
	
	public Product() {
		this.active = true;
	}

	public Product(String name, String description, BigDecimal price, Category category) {
		this();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

}
