package medico.api.medApi.domain.medico.dto;

import jakarta.validation.constraints.NotNull;
import medico.api.medApi.domain.endereco.dto.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco) {
}
