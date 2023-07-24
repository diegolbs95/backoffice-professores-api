package com.backoffice.professores.infra.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends PortalAPIException{

    public UserNotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }
}
