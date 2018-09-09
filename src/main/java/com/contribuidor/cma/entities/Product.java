package com.contribuidor.cma.entities;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;

	private String description;

	private BigDecimal price;

	@ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_category")
	private Category category;

	private String storeId;

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
