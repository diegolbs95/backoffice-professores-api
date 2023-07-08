package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.usercase.dto.AuthenticationRequest;
import com.backoffice.professores.usercase.dto.AuthenticationResponse;
import com.backoffice.professores.usercase.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerImplTest {

    @Mock
    AuthService service;

    @InjectMocks
    AuthControllerImpl authController;

    @Test
    void authenticate() {
        var response = AuthenticationResponse.builder()
                .refreshToken("refreshSucesso")
                .accessToken("sucesso")
                .build();

        var request = AuthenticationRequest.builder()
                .password("backoffice123")
                .email("test@backoffice.com")
                .build();

        when(service.authenticate(any())).thenReturn(response);

        var result = authController.authenticate(request);

        assertEquals(response, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}