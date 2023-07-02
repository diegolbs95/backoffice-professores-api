package com.backoffice.professores.infra.controller;

import com.backoffice.professores.usercase.dto.ProfessorDTO;
import org.springframework.http.ResponseEntity;

public interface ProfessorController {

    ResponseEntity<ProfessorDTO> registro(ProfessorDTO professorDTO);

}
