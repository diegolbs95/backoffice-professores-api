package com.backoffice.professores.infra.controller;

import com.backoffice.professores.usercase.dto.AulaDTO;

import java.util.List;

public interface AulaController {

    AulaDTO registro(String token, AulaDTO aulaDTO);
    List<AulaDTO> listar(String token);
    void atualizar(String token, String id, AulaDTO aulaDTO);
}
