package medico.api.medApi.domain.usuario.dto;

import jakarta.validation.constraints.NotNull;
import medico.api.medApi.domain.usuario.Roles;

public record DadosAtualizacaoUsuario(
        @NotNull
        Long id,
        String login,
        String senha,
        Roles role) {

}
