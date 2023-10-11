package medico.api.medApi.domain.paciente.dto;

import medico.api.medApi.domain.endereco.Endereco;
import medico.api.medApi.domain.paciente.Paciente;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCpf(), medico.getTelefone(), medico.getEndereco());
    }
}
