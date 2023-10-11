package medico.api.medApi.controller;

import jakarta.validation.Valid;
import medico.api.medApi.domain.usuario.*;
import medico.api.medApi.domain.usuario.dto.DadosAutenticacao;
import medico.api.medApi.domain.usuario.dto.DadosCadastroUsuario;
import medico.api.medApi.domain.usuario.dto.DadosDetalhamentoUsuario;
import medico.api.medApi.infra.security.DadosTokenJWT;
import medico.api.medApi.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/public")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
       var AuthenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
       var authentication = manager.authenticate(AuthenticationToken);

       var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

       return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var existUser = userRepository.findByLogin(dados.login());
        if (existUser != null) {
            return ResponseEntity.badRequest().body("Login já está em uso. Escolha outro login.");
        }
        var usuario = new Usuario(dados);
        Usuario createdUsuario = userService.createUserAuth(usuario);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(createdUsuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }


}
