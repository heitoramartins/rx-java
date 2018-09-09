package com.contribuidor.cma.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product")
    private Product product;

    @ManyToOne(targetEntity = Budget.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_budget")
    private Budget budget;

    private Integer quantity;
}
