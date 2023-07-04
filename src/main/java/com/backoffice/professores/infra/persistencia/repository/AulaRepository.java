package com.backoffice.professores.infra.persistencia.repository;

import com.backoffice.professores.infra.persistencia.domain.Aula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaRepository extends MongoRepository<Aula, String> {

    List<Aula> findAllByProfessorId(String professorId);
}
