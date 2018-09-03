package com.contribuidor.cma.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table
@Entity(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;

	@NotNull
	private Boolean active;

	public Category() {
		this.active = true;
	}
	
	public Category(String name) {
		this();
		this.name = name;
	}

	public void inactive(){
		this.active = false;
	}

}
