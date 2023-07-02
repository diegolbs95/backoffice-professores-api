package com.backoffice.professores.usercase.service;

import com.backoffice.professores.usercase.dto.AulaDTO;

import java.util.List;

public interface AulaService {

    AulaDTO registro(String professorToken, AulaDTO aulaDTO);

    List<AulaDTO> listar(String professorToken);

    void atualizar(String emailProfessor, Long idAula, AulaDTO aulaDTO);
}
