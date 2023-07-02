package com.backoffice.professores.infra.controller;

import com.backoffice.professores.usercase.dto.AulaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AulaController {

    ResponseEntity<AulaDTO> registro(String token, AulaDTO aulaDTO);
    ResponseEntity<List<AulaDTO>> listar(String token);
    void atualizar(String token, Long id, AulaDTO aulaDTO);
}
