package com.estech.api_vendas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estech.api_vendas.dto.UsuarioDTO;
import com.estech.api_vendas.dto.UsuarioResponseDTO;
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

  public Page<UsuarioResponseDTO> listar(String nome, String email, Pageable pageable) {

    Page<Usuario> page;

    if (nome != null) {
      page = repository.findByNomeContainingIgnoreCase(nome, pageable);
    } else if (email != null) {
      page = repository.findByEmailContainingIgnoreCase(email, pageable);
    } else {
      page = repository.findAll(pageable);
    }

    return page.map(usuario -> new UsuarioResponseDTO(
        usuario.getId(),
        usuario.getNome(),
        usuario.getEmail()));
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
