package com.backoffice.professores.usercase.service.impl;

import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.infra.persistencia.repository.ProfessorRepository;
import com.backoffice.professores.usercase.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.backoffice.professores.infra.persistencia.enums.Role.ADMIN;
import static com.backoffice.professores.infra.persistencia.enums.Role.PROFESSOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BackofficeServiceImplTest {

    @Mock
    ProfessorRepository professorRepository;
    @Mock
    ProfessorService professorService;

    @InjectMocks
    BackofficeServiceImpl backofficeServiceImpl;

    String token;
    String emailProfessor;

    @BeforeEach
    void setUp() {
        token = "testToken";
        emailProfessor = "test@backoffice.com";
    }

    @Test
    void testAprovarRegistroProfessorValidandoTokenRoleAdmin() {

        var professorAdmin = Professor.builder()
                .role(ADMIN)
                .build();

        when(professorService.buscarProfessorNoToken(token)).thenReturn(professorAdmin);
        when(professorRepository.findByEmail(emailProfessor)).thenReturn(Optional.of(new Professor()));

        var result = backofficeServiceImpl.aprovarRegistroProfessor(token, emailProfessor);

        assertEquals("CADASTRO_APROVADO", result);
        verify(professorRepository, times(1)).save(any());
    }

    @Test
    void testAprovarRegistroProfessorValidandoTokenRoleProfessorPermissaoNegada(){

        var professor = Professor.builder()
                .role(PROFESSOR)
                .build();

        when(professorService.buscarProfessorNoToken(token)).thenReturn(professor);

        assertEquals("USUARIO_NAO_TEM_PERMISSAO_PARA_APROVAR_CADASTRO",
                backofficeServiceImpl.aprovarRegistroProfessor(token, emailProfessor));
    }

    @Test
    void testAprovarRegistroProfessorEmailNaoEncontrado() {

        var professorAdmin = Professor.builder()
                .role(ADMIN)
                .build();

        when(professorService.buscarProfessorNoToken(token)).thenReturn(professorAdmin);

        assertThrows(UsernameNotFoundException.class, () -> {
            backofficeServiceImpl.aprovarRegistroProfessor(token, emailProfessor);
        });

        verify(professorRepository, never()).save(any());
    }
}