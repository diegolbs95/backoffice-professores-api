package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.usercase.dto.AulaDTO;
import com.backoffice.professores.usercase.service.AulaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AulaControllerImplTest {

    @Mock
    AulaService service;

    @InjectMocks
    AulaControllerImpl aulaController;

    String token;
    AulaDTO aulaDTO;

    @BeforeEach
    void setUp() {

        token = "tokenAutenticado";

    }

    @Test
    void registro() {

        when(service.registro(token, aulaDTO)).thenReturn(aulaDTO);

        var result = aulaController.registro(token, aulaDTO);

        assertEquals(aulaDTO, result);
    }

    @Test
    void listar() {

        when(service.listar(token)).thenReturn(new ArrayList<AulaDTO>());

        aulaController.listar(token);
        verify(service, times(1)).listar(anyString());
    }

    @Test
    void atualizar() {

        aulaController.atualizar(token, "1", aulaDTO);

        verify(service, times(1)).atualizar(anyString(), anyString(), any());

    }
}