package medico.api.medApi.domain.medico;

import jakarta.validation.constraints.NotNull;
import medico.api.medApi.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco) {
}
