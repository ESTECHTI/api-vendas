package com.estech.api_vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estech.api_vendas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
