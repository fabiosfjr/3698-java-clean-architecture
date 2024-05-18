package br.com.alura.codechella.infra.controller;

import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuario criarUsuario;

    public UsuarioController(CriarUsuario criarUsuario) {
        this.criarUsuario = criarUsuario;
    }

    @PostMapping
    public UsuarioDto cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioSalvoNoBanco = criarUsuario.cadastrarUsuario(new Usuario(usuarioDto.cpf(), usuarioDto.nome(),
                usuarioDto.nascimento(), usuarioDto.email()));

        return new UsuarioDto(usuarioSalvoNoBanco.getCpf(), usuarioSalvoNoBanco.getNome(),
                usuarioSalvoNoBanco.getNascimento(), usuarioSalvoNoBanco.getEmail());
    }



}
