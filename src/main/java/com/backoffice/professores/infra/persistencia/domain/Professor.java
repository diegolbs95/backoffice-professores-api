package com.backoffice.professores.infra.persistencia.domain;

import com.backoffice.professores.infra.persistencia.enums.Role;
import com.backoffice.professores.infra.persistencia.enums.StatusProfessor;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tb_professores")
public class Professor implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    @Setter
    private String senha;
    private String nome;

    @Setter
    private StatusProfessor status;

    @Field(targetType = FieldType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
