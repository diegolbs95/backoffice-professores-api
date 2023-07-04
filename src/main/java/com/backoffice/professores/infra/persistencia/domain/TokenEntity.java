package com.backoffice.professores.infra.persistencia.domain;

import com.backoffice.professores.infra.persistencia.enums.TokenType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tb_tokens")
public class TokenEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String token;

    @Field(targetType = FieldType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    @Setter
    private boolean revoked;
    @Setter
    private boolean expired;

    @DBRef
    private Professor professor;

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
