package medico.api.medApi.controller;

import jakarta.validation.Valid;
import medico.api.medApi.domain.usuario.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UsuarioRepository repository;

    public UserController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var existUser = repository.findByLogin(dados.login());
        if ( existUser != null) {
            return ResponseEntity.badRequest().body("Login já está em uso. Escolha outro login.");
        }
        var usuario = new Usuario(dados);
        repository.save(usuario);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(sort = {"login"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/all")
    public ResponseEntity<Stream<DadosListagemUsuario>> listar( ){
        var user = repository.findAll().stream().map(DadosListagemUsuario::new);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<DadosAtualizacaoUsuario> excluir(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> restaurar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.recuperarPaciente();

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

}