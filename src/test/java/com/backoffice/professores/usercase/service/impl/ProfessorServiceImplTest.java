package com.backoffice.professores.usercase.service.impl;

import com.backoffice.professores.infra.config.security.JwtService;
import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.infra.persistencia.repository.ProfessorRepository;
import com.backoffice.professores.infra.persistencia.repository.TokenRepository;
import com.backoffice.professores.usercase.dto.ProfessorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProfessorServiceImplTest {

    @Mock
    ProfessorRepository professorRepository;
    @Mock
    JwtService jwtService;
    @Mock
    TokenRepository tokenRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    ProfessorServiceImpl professorService;

    @Test
    void testRegistroProfessor() {

        var professorDTO = ProfessorDTO.builder()
                .senha("123test")
                .email("test@backoffice.com")
                .build();

        when(passwordEncoder.encode(anyString())).thenReturn("SenhaCryptor");
        when(professorRepository.save(any())).thenReturn(new Professor());

        assertNotNull(professorService.registro(professorDTO));
        verify(professorRepository, times(1)).save(any());
    }

    @Test
    void testBuscarProfessorNoToken() {
        var token = "Bearer testToken";
        var professor = Professor.builder()
                .nome("Test")
                .build();

        when(jwtService.extractUsername(anyString())).thenReturn("test@backoffice.com");
        when(professorRepository.findByEmail(anyString())).thenReturn(Optional.of(professor));

        assertEquals(professor, professorService.buscarProfessorNoToken(token));
    }
    @Test
    void testBuscarProfessorNoTokenEmailNaoEncontrado() {
        var token = "Bearer testTokenInvalid";

        when(jwtService.extractUsername(anyString())).thenReturn("test@backoffice.com");

        assertThrows(UsernameNotFoundException.class, () -> {
            professorService.buscarProfessorNoToken(token);
        });
    }

    @Test
    void testBuscarProfessorNoTokenNull() {
        assertNull(professorService.buscarProfessorNoToken(null));
    }


    @Test
    void testSalvarTokenProfessor() {
        var token = "testToken";

        professorService.salvarTokenProfessor(new Professor(), token);
        verify(tokenRepository, times(1)).save(any());
    }
}