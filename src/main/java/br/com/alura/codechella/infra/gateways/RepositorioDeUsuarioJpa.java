package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infra.persistence.UsuarioEntity;
import br.com.alura.codechella.infra.persistence.UsuarioRepository;

import java.util.List;

public class RepositorioDeUsuarioJpa implements RepositorioDeUsuario {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public RepositorioDeUsuarioJpa(UsuarioRepository usuarioRepository, UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioEntityMapper.toEntity(usuario);
        usuarioRepository.save(usuarioEntity);
        return usuarioEntityMapper.toDomain(usuarioEntity);
    }

    @Override
    public List<Usuario> listarTodos() {
        //return usuarioRepository.findAll();
        return null;
    }
}
