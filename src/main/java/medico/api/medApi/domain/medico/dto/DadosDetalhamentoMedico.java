package medico.api.medApi.domain.medico.dto;

import medico.api.medApi.domain.endereco.Endereco;
import medico.api.medApi.domain.medico.Especialidade;
import medico.api.medApi.domain.medico.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco) {

    public DadosDetalhamentoMedico (Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }

}
