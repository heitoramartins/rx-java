package com.contribuidor.cma.dto.error;

import com.contribuidor.cma.exception.fielderror.FieldErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FieldErrorResponseDto {

    @JsonProperty("erros")
    private List<FieldErrorMessage> fieldErrorMessages;

    public FieldErrorResponseDto(List<FieldErrorMessage> fieldErrorMessages) {
        this.fieldErrorMessages = fieldErrorMessages;
    }

    public List<FieldErrorMessage> getFieldErrorMessages() {
        return fieldErrorMessages;
    }

    public void setFieldErrorMessages(List<FieldErrorMessage> fieldErrorMessages) {
        this.fieldErrorMessages = fieldErrorMessages;
    }
}
