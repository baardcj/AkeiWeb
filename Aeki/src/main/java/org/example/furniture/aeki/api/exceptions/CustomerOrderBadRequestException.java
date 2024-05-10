package org.example.furniture.aeki.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerOrderBadRequestException extends RuntimeException {

    public CustomerOrderBadRequestException(String message) {
        super(message);
    }
}