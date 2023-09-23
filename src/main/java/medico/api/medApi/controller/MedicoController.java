package medico.api.medApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medico.api.medApi.medico.DadosCadastroMedico;
import medico.api.medApi.medico.Medico;
import medico.api.medApi.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }
}