package medico.api.medApi.domain.medico.dto;

import medico.api.medApi.domain.medico.Especialidade;
import medico.api.medApi.domain.medico.Medico;

public record DadosListagemMedico(
        long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade

     ) {

     public DadosListagemMedico(Medico medico){
          this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
     }
}
