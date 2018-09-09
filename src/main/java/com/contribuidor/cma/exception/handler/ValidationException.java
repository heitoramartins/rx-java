package com.contribuidor.cma.exception.handler;

import com.contribuidor.cma.exception.fielderror.ErrorMessage;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.List;

public class ValidationException extends RuntimeException {

    private List<ErrorMessage> errorMessages = Collections.EMPTY_LIST;
    private HttpStatus httpStatus;

    public ValidationException(List<ErrorMessage> errorMessages, HttpStatus httpStatus) {
        this.errorMessages = errorMessages;
        this.httpStatus = httpStatus;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
 }
