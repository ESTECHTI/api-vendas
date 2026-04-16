package com.estech.api_vendas.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estech.api_vendas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

  Page<Usuario> findByEmailContainingIgnoreCase(String email, Pageable pageable);

  Optional<Usuario> findByEmail(String email);
}
