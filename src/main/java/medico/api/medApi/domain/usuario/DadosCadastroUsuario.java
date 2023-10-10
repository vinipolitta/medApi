package medico.api.medApi.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank(message = "Email é obrigatório")
        String login,
        @NotBlank(message = "senha é obrigatório")
        String senha
      ) {
    public DadosCadastroUsuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

}