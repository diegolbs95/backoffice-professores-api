package com.backoffice.professores.infra.persistencia.domain;

import com.backoffice.professores.infra.persistencia.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    @Setter
    private boolean revoked;
    @Setter
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;
}
