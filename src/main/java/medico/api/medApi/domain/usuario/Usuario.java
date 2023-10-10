package medico.api.medApi.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    private String role = "ROLE_USER";
    private boolean ativo = true;

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
        if (dados.login() != null) {
            this.login = dados.login();
        }
        if (dados.role() != null) {
            this.role = dados.role();
        }
    }

    public Usuario(DadosCadastroUsuario dados) {
        this.login = dados.login();
        this.senha = dados.senha();
    }

    public void excluir() {
        this.ativo = false;
    }

    public void recuperarPaciente() {
        this.ativo = true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public boolean getAtivo() {
        return ativo;
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

    public void setPassword(String senha) {
        this.senha = senha;
    }

}

