package com.contribuidor.cma.service.budget;

import com.contribuidor.cma.entities.Budget;

import java.math.BigDecimal;

public enum TaxEnum {

    ICMS
            {
                public BigDecimal icms(Budget budget){
                    return budget.getTotal().add(budget.getTotal().multiply(new BigDecimal(Tax.ICMS.getTax())));
                }
            },

    ISS
            {
                public BigDecimal iss(Budget budget){
                    return budget.getTotal().add(budget.getTotal().multiply(new BigDecimal(Tax.ISS.getTax())));
                }
            };
}
