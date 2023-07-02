package com.backoffice.professores.infra.persistencia.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permisao {

    BACKOFFICE_CREATE("backoffice:create"),
    BACKOFFICE_UPDATE("backoffice:update"),
    BACKOFFICE_READ("backoffice:read"),
    PROFESSOR_READ("professor:read"),
    PROFESSOR_UPDATE("professor:update"),
    PROFESSOR_CREATE("professor:create");

    @Getter
    private final String permisoes;
}
