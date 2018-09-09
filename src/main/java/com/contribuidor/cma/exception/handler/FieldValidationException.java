package com.contribuidor.cma.exception.handler;

import com.contribuidor.cma.exception.fielderror.FieldErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class FieldValidationException extends RuntimeException {

    private List<FieldErrorMessage> fieldErrorMessage;
    private HttpStatus httpStatus;

    public FieldValidationException(FieldErrorMessage fieldErrorMessage, HttpStatus httpStatus) {
        this.fieldErrorMessage = Arrays.asList(fieldErrorMessage);
        this.httpStatus = httpStatus;
    }

    public FieldValidationException(List<FieldErrorMessage> fieldErrorMessage, HttpStatus httpStatus) {
        this.fieldErrorMessage = fieldErrorMessage;
        this.httpStatus = httpStatus;
    }

    public List<FieldErrorMessage> getFieldErrorMessages() {
        return fieldErrorMessage;
    }

    public void setFieldErrorMessage(List<FieldErrorMessage> fieldErrorMessage) {
        this.fieldErrorMessage = fieldErrorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
