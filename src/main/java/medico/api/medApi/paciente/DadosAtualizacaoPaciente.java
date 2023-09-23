package medico.api.medApi.paciente;

import jakarta.validation.constraints.NotNull;
import medico.api.medApi.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
