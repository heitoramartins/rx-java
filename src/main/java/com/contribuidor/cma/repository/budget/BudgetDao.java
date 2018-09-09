package com.contribuidor.cma.repository.budget;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class BudgetDao {

    @Autowired
    private EntityManager em;

    @Autowired
    private BudgetRepository repository;

    public BudgetRepository getRepository(){
        return repository;
    }


}
