package com.contribuidor.cma.service.budget;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public enum Tax {

    ISS(new BigDecimal(0.03),"ISS"),
    ICMS(new BigDecimal(0.05),"ICMS");

    private final BigDecimal value;
    private final String tax;

    Tax(BigDecimal value, String tax) {
        this.value = value;
        this.tax = tax;
    }

    public BigDecimal getValue() {
        return value;
    }

    @JsonValue
    public String getTax() {
        return tax;
    }
}
