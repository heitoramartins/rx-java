package com.contribuidor.cma.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "category")
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
