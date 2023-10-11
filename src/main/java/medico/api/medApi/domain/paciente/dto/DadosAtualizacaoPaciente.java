package medico.api.medApi.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import medico.api.medApi.domain.endereco.dto.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
