package com.estech.api_vendas.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.estech.api_vendas.entity.Usuario;
import com.estech.api_vendas.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UsuarioRepository repository;

  public CustomUserDetailsService(UsuarioRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Usuario usuario = repository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    return new UsuarioUserDetails(usuario);
  }
}
