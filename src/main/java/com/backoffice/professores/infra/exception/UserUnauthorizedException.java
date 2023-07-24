package com.backoffice.professores.infra.exception;

import org.springframework.http.HttpStatus;

public class UserUnauthorizedException extends PortalAPIException {

    public UserUnauthorizedException(final String message) {
        super(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
