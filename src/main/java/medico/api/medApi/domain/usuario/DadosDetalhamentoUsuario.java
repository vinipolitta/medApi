package medico.api.medApi.domain.usuario;

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
                usuario.getRole(),
                usuario.getAtivo()
        );
    }

}
