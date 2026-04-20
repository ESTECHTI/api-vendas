package com.estech.api_vendas.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.estech.api_vendas.entity.Usuario;

public class UsuarioUserDetails implements UserDetails {

  private final Usuario usuario;

  public UsuarioUserDetails(Usuario usuario) {
    this.usuario = usuario;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> usuario.getRole());
  }

  @Override
  public String getPassword() {
    return usuario.getSenha();
  }

  @Override
  public String getUsername() {
    return usuario.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
