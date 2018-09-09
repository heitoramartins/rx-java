package com.contribuidor.cma.exception.fielderror;

public class ErrorMessage {

    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public static ErrorMessage createGlobalError(String message) {
        return new ErrorMessage(message);
    }
}
