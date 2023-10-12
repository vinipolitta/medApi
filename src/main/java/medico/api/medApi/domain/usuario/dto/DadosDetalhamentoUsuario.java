package medico.api.medApi.domain.usuario.dto;

import medico.api.medApi.domain.usuario.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String role,
        boolean ativo
   ) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                String.valueOf(usuario.getRole()),
                usuario.getAtivo()
        );
    }

}
