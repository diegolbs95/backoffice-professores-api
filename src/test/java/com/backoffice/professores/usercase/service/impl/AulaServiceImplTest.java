package com.backoffice.professores.usercase.service.impl;

import com.backoffice.professores.infra.exception.UserNotFoundException;
import com.backoffice.professores.infra.persistencia.domain.Aula;
import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.infra.persistencia.enums.StatusProfessor;
import com.backoffice.professores.infra.persistencia.repository.AulaRepository;
import com.backoffice.professores.usercase.dto.AulaDTO;
import com.backoffice.professores.usercase.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AulaServiceImplTest {

    @Mock
    AulaRepository aulaRepository;
    @Mock
    ProfessorService professorService;

    @InjectMocks
    AulaServiceImpl aulaService;

    Professor professor;
    AulaDTO aulaDTO;
    Aula aula;

    @BeforeEach
    void setUp() {

        professor = Professor.builder()
                .id("1")
                .email("test@backoffice.com")
                .nome("Backoffice")
                .status(StatusProfessor.APROVADO)
                .build();

        aulaDTO = AulaDTO.builder()
                .dataPrevistaAula("2023-06-10")
                .descricao("Revisão Prova")
                .titulo("Matematica")
                .id("1")
                .build();

        aula = Aula.builder()
                .dataPrevistaAula(LocalDate.now())
                .descricao("Revisão Prova")
                .titulo("Matematica")
                .id("1")
                .professor(professor)
                .build();
    }

    @Test
    void testRegistroAula() {

        when(professorService.buscarProfessorNoToken(anyString())).thenReturn(professor);

        assertEquals(aulaDTO, aulaService.registro("testTokenProfessor", aulaDTO));
        verify(aulaRepository, times(1)).save(any());
    }

    @Test
    void testRegistroAulaProfessorNaoExistente() {

        when(professorService.buscarProfessorNoToken(anyString()))
                .thenThrow(new UserNotFoundException("Professor não encontrado"));

        try {
            aulaService.registro("testTokenProfessor", aulaDTO);
        }catch (UserNotFoundException e) {
            assertEquals("Professor não encontrado", e.getMessage());
            verify(aulaRepository, never()).save(any());
        }
    }

    @Test
    void testListarAulas() {
        var listAulas = new ArrayList<Aula>();
        listAulas.add(aula);

        when(professorService.buscarProfessorNoToken("testTokenProfessor")).thenReturn(professor);
        when(aulaRepository.findAllByProfessorId(anyString())).thenReturn(listAulas);

        assertNotNull(aulaService.listar("testTokenProfessor"));
    }

    @Test
    void testAtualizarAula() {

        when(professorService.buscarProfessorNoToken(anyString())).thenReturn(professor);
        when(aulaRepository.findById(anyString())).thenReturn(Optional.ofNullable(aula));

        aulaService.atualizar("test@backoffice.com", "1", aulaDTO);

        verify(aulaRepository, times(1)).save(aula);
    }

    @Test
    void testAtualizarAulaNaoEncontrada() {
        when(professorService.buscarProfessorNoToken(anyString())).thenReturn(professor);

        assertThrows(UserNotFoundException.class, () -> {
            aulaService.atualizar("test@backoffice.com", "1", aulaDTO);
        });
        verify(aulaRepository, never()).save(any());
    }
}