package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.infra.controller.ProfessorController;
import com.backoffice.professores.usercase.dto.ProfessorDTO;
import com.backoffice.professores.usercase.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
public class ProfessorControllerImpl implements ProfessorController {

    private final ProfessorService service;

    @Override
    @PostMapping("/registro")
    public ResponseEntity<ProfessorDTO> registro(@RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.ok(service.registro(professorDTO));
    }
}
