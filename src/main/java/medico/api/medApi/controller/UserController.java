package medico.api.medApi.controller;

import jakarta.validation.Valid;
import medico.api.medApi.domain.usuario.*;
import medico.api.medApi.domain.usuario.dto.DadosAtualizacaoUsuario;
import medico.api.medApi.domain.usuario.dto.DadosDetalhamentoUsuario;
import medico.api.medApi.domain.usuario.dto.DadosListagemUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/admin")
public class UserController {
    private final UsuarioRepository repository;
    public UserController(UsuarioRepository repository) {
        this.repository = repository;
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

    @DeleteMapping("{id}")
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