package medico.api.medApi.domain.consulta.validacoes;

import medico.api.medApi.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar (DadosAgendamentoConsulta dados);
}
