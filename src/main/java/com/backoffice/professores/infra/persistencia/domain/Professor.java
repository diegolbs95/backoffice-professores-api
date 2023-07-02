package com.backoffice.professores.infra.persistencia.domain;

import com.backoffice.professores.infra.persistencia.enums.Role;
import com.backoffice.professores.infra.persistencia.enums.StatusProfessor;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_professors")
public class Professor implements UserDetails {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String senha;
    private String nome;

    private StatusProfessor status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "professor")
    private List<Aula> aulas;

    @OneToMany(mappedBy = "professor")
    private List<TokenEntity> tokenEntitys;

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
