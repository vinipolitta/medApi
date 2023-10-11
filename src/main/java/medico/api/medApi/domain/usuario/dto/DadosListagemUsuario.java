package medico.api.medApi.domain.usuario.dto;

import medico.api.medApi.domain.usuario.Usuario;

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
