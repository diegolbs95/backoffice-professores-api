package com.backoffice.professores.infra.persistencia.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.backoffice.professores.infra.persistencia.enums.Permisao.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    BACKOFFICE_CREATE,
                    BACKOFFICE_UPDATE,
                    BACKOFFICE_READ,
                    PROFESSOR_READ,
                    PROFESSOR_UPDATE,
                    PROFESSOR_CREATE
            )
    ),
    PROFESSOR(
            Set.of(
                    PROFESSOR_READ,
                    PROFESSOR_UPDATE,
                    PROFESSOR_CREATE
            )
    )

    ;

    @Getter
    private final Set<Permisao> permissoes;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissoes()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermisoes()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
