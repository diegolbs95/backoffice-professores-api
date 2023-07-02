package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.infra.controller.BackofficeController;
import com.backoffice.professores.usercase.service.BackofficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/backoffice")
@RequiredArgsConstructor
public class BackofficeControllerImpl implements BackofficeController {

    private final BackofficeService backofficeService;

    @Override
    @PutMapping("/aprovar-cadastro")
    public ResponseEntity<String> aprovar(@RequestHeader("Authorization") String token, @RequestHeader String email) {
        return ResponseEntity.ok(backofficeService.aprovarRegistroProfessor(token, email));
    }
}
