package com.contribuidor.cma.exception.handler;


import com.contribuidor.cma.dto.error.ErrorResponseDto;
import com.contribuidor.cma.dto.error.FieldErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandller extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ValidationException.class })
    protected ResponseEntity<ErrorResponseDto> handleCbcValidationException(ValidationException exception, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(exception.getErrorMessages());

        return new ResponseEntity<ErrorResponseDto>(errorResponse, exception.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {FieldValidationException.class })
    protected ResponseEntity<FieldErrorResponseDto> handleCbcValidationException(FieldValidationException exception, WebRequest request) {
        FieldErrorResponseDto fieldErrorResponse = new FieldErrorResponseDto(exception.getFieldErrorMessages());

        return new ResponseEntity<FieldErrorResponseDto>(fieldErrorResponse, exception.getHttpStatus());
    }
}
