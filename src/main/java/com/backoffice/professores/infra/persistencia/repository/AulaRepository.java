package com.backoffice.professores.infra.persistencia.repository;

import com.backoffice.professores.infra.persistencia.domain.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
}
