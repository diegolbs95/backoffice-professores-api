package com.backoffice.professores.infra.persistencia.repository;

import com.backoffice.professores.infra.persistencia.domain.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query(value = """
      select t from TokenEntity t inner join Professor u\s
      on t.professor.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenEntity> findAllValidTokenByProfessor(Long id);

    Optional<TokenEntity> findByToken(String token);
}
