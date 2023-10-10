package medico.api.medApi.domain.usuario;

public record DadosListagemUsuario(
        long id,
        String login,
        String senha,
        String role,
        boolean ativo

     ) {

     public DadosListagemUsuario(Usuario medico){
          this(medico.getId(), medico.getLogin(), medico.getSenha(), medico.getRole(), medico.getAtivo());
     }
}
