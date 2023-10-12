package medico.api.medApi.controller;

// Trecho de código suprimido

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medico.api.medApi.domain.consulta.AgendaDeConsultas;
import medico.api.medApi.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
       var dto =  agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

}