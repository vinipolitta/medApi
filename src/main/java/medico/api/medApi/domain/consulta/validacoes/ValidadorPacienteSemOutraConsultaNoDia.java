package medico.api.medApi.domain.consulta.validacoes;

import medico.api.medApi.domain.consulta.ConsultaRepository;
import medico.api.medApi.domain.consulta.DadosAgendamentoConsulta;
import medico.api.medApi.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta  {


    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
       var primeiroHorario = dados.data().withHour(7);
       var ultimoHorario = dados.data().withHour(18);

       var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
       if (pacientePossuiOutraConsultaNoDia) {
           throw new ValidacaoException("Paciente ja possui uma consulta agendada nesse dia");
       }

    }
}
