package com.contribuidor.cma.exception.fielderror;

public class FieldErrorMessage {

    private String field;
    private String message;

    private FieldErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static FieldErrorMessage createFieldError(String field, String message) {
        return new FieldErrorMessage(field, message);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
