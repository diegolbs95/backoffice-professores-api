package com.backoffice.professores.infra.exception;

import lombok.Getter;

@Getter
public abstract class PortalAPIException extends RuntimeException {

    private final int code;

    PortalAPIException(final int code, final String message) {
        super(message);
        this.code = code;
    }
}
