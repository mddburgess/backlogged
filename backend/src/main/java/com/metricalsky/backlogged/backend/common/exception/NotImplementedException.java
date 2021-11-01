package com.metricalsky.backlogged.backend.common.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class NotImplementedException extends RuntimeException {

    public NotImplementedException(String message, Object... arguments) {
        super(MessageFormat.format(message, arguments));
    }
}
