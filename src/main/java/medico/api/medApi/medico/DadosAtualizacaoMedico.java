package medico.api.medApi.medico;

import jakarta.validation.constraints.NotNull;
import medico.api.medApi.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEndereco endereco) {
}
