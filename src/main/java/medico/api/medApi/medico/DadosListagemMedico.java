package medico.api.medApi.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medico.api.medApi.endereco.DadosEndereco;

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
