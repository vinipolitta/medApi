package medico.api.medApi.domain.consulta.validacoes;

import medico.api.medApi.domain.consulta.DadosAgendamentoConsulta;
import medico.api.medApi.domain.paciente.PacienteRepository;
import medico.api.medApi.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta  {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados)  {
        if (dados.idPaciente() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta nao pode ser agendada com paciente inativo");
        }
    }
}
