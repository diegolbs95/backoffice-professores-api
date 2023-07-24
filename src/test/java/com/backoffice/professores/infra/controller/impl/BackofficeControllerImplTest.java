package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.usercase.service.BackofficeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BackofficeControllerImplTest {

    @Mock
    BackofficeService backofficeService;

    @InjectMocks
    BackofficeControllerImpl backofficeController;

    @Test
    void aprovar() {
        var token = "tokenProfessor";
        var emailProfessor = "test@backoffice.com";
        var response = "CADASTRO_APROVADO";

        when(backofficeService.aprovarRegistroProfessor(token,
                emailProfessor)).thenReturn(response);

        var result = backofficeController.aprovar(token, emailProfessor);

        assertEquals(response, result);
    }
}