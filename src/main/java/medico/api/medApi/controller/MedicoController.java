package medico.api.medApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medico.api.medApi.medico.DadosCadastroMedico;
import medico.api.medApi.medico.DadosListagemMedico;
import medico.api.medApi.medico.Medico;
import medico.api.medApi.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
}