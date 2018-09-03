package com.contribuidor.cma.contract;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRegistration {

	@NotNull
	private String name;

	private String description;

	@NotNull
	private BigDecimal price;

	@NotNull
	private CategoryRegistration category;

	public static class CategoryRegistration {

		private Long id;
		private String name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
