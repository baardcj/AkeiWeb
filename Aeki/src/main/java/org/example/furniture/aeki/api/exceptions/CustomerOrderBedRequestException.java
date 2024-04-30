package org.example.furniture.aeki.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerOrderBedRequestException  extends RuntimeException {

    public CustomerOrderBedRequestException(String message) {
        super(message);
    }
}