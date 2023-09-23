package medico.api.medApi.paciente;

import medico.api.medApi.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCpf(), medico.getTelefone(), medico.getEndereco());
    }
}
