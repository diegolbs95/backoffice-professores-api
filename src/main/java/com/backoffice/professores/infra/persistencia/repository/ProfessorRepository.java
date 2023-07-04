package com.backoffice.professores.infra.persistencia.repository;

import com.backoffice.professores.infra.persistencia.domain.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends MongoRepository<Professor, String> {

    Optional<Professor> findByEmail(String email);
}
