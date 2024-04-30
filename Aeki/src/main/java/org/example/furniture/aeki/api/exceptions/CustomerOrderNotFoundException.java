package org.example.furniture.aeki.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerOrderNotFoundException extends RuntimeException {

    public CustomerOrderNotFoundException(Long id) { // todo change name
        super("Could not find customer order " + id);
    }
}
