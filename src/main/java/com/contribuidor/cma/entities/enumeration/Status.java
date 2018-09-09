package com.contribuidor.cma.entities.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    FALSE(0, "false"), TRUE(1, "true");

    private final int id;
    private final String label;

    Status(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.getLabel().equals(value)) {
                return status;
            }
        }
        return null;
    }


    @JsonValue
    public String getLabel() {
        return label;
    }
}
