package com.estech.api_vendas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estech.api_vendas.dto.UsuarioDTO;
import com.estech.api_vendas.entity.Usuario;
import com.estech.api_vendas.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
  private final UsuarioService service;

  public UsuarioController(UsuarioService service) {
    this.service = service;
  }

  @PostMapping
  public Usuario criar(@Valid @RequestBody UsuarioDTO dto) {
    return service.criar(dto);
  }

  @GetMapping
  public List<Usuario> lista() {
    return service.listar();
  }
}
