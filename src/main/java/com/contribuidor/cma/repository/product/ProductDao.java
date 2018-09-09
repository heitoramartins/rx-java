package com.contribuidor.cma.repository.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ProductDao {

    @Autowired
    private EntityManager em;

    @Autowired
    private ProductRepository repository;

    public ProductRepository getRepository(){
        return repository;
    }
}
