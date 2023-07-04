package com.backoffice.professores.infra.persistencia.repository;

import com.backoffice.professores.infra.persistencia.domain.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<TokenEntity, String> {

    List<TokenEntity> findAllByProfessorIdAndExpiredIsFalseAndRevokedIsFalse(String id);

    Optional<TokenEntity> findByToken(String token);
}
