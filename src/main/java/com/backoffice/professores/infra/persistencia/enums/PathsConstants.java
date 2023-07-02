package com.backoffice.professores.infra.persistencia.enums;

public final class PathsConstants {

    private PathsConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PATH_API = "/api/v1/";
    public static final String PATH_PROFESSOR = PATH_API.concat("professor/");
    public static final String PATH_BACKOFFICE = PATH_API.concat("backoffice/");
    public static final String PATH_AUTH= PATH_API.concat("auth/");
    public static final String PATH_AULA = PATH_API.concat("aula/");
}
