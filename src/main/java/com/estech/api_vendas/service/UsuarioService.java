package com.estech.api_vendas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estech.api_vendas.dto.UsuarioDTO;
import com.estech.api_vendas.entity.Usuario;
import com.estech.api_vendas.exception.ResourceNotFoundException;
import com.estech.api_vendas.repository.UsuarioRepository;

@Service
public class UsuarioService {
  private final UsuarioRepository repository;

  public UsuarioService(UsuarioRepository repository) {
    this.repository = repository;
  }

  public Usuario criar(UsuarioDTO dto) {
    Usuario usuario = new Usuario();
    usuario.setNome(dto.getNome());
    usuario.setEmail(dto.getEmail());

    return repository.save(usuario);
  }

  public List<Usuario> listar() {
    return repository.findAll();
  }

  public Usuario buscarPorId(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
  }

  public Usuario atualizar(Long id, UsuarioDTO dto) {
    Usuario usuario = buscarPorId(id);

    usuario.setNome(dto.getNome());
    usuario.setEmail(dto.getEmail());

    return repository.save(usuario);
  }

  public void deletar(Long id) {
    Usuario usuario = buscarPorId(id);
    repository.delete(usuario);
  }
}
