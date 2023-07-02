package com.backoffice.professores.infra.controller;

import org.springframework.http.ResponseEntity;

public interface BackofficeController {

    ResponseEntity<String> aprovar(String token, String email);
}
