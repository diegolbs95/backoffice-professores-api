package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.usercase.dto.ProfessorDTO;
import com.backoffice.professores.usercase.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfessorControllerImplTest {

    @Mock
    ProfessorService service;

    @InjectMocks
    ProfessorControllerImpl professorController;

    @Test
    void registro() {
        var professorDTO = ProfessorDTO.builder()
                .email("test@backoffice.com")
                .nome("Backoffice")
                .senha("backoffice123")
                .build();

        when(service.registro(professorDTO)).thenReturn(professorDTO);

        var result = professorController.registro(professorDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(professorDTO, result.getBody());
    }
}