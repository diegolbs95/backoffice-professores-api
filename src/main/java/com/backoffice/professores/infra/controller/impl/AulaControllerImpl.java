package com.backoffice.professores.infra.controller.impl;

import com.backoffice.professores.infra.controller.AulaController;
import com.backoffice.professores.usercase.dto.AulaDTO;
import com.backoffice.professores.usercase.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aula")
@RequiredArgsConstructor
public class AulaControllerImpl implements AulaController {

    private final AulaService service;

    @Override
    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public AulaDTO registro(@RequestHeader("Authorization") String token, @RequestBody AulaDTO aulaDTO) {
        return service.registro(token, aulaDTO);
    }

    @Override
    @GetMapping("/listar")
    public List<AulaDTO> listar(@RequestHeader("Authorization") String token) {
        return service.listar(token);
    }

    @Override
    @PutMapping("/atualizar/{id}")
    public void atualizar(@RequestHeader("Authorization") String token, @PathVariable String id,@RequestBody AulaDTO aulaDTO) {
        service.atualizar(token, id, aulaDTO);
    }
}
