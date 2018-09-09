package com.contribuidor.cma.dto.error;

import com.contribuidor.cma.exception.fielderror.ErrorMessage;

import java.util.List;

public class ErrorResponseDto {

    private List<ErrorMessage> messages;

    public ErrorResponseDto(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ErrorMessage> messages) {
        this.messages = messages;
    }
}

