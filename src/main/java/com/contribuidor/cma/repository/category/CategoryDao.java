package com.contribuidor.cma.repository.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CategoryDao {

    @Autowired
    private EntityManager em;

    @Autowired
    private CategoryRepository repository;

    public CategoryRepository getRepository(){
        return repository;
    }
}
