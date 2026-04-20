package com.estech.api_vendas.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estech.api_vendas.dto.UsuarioDTO;
import com.estech.api_vendas.dto.UsuarioResponseDTO;
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
  public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioDTO dto) {
    return service.criar(dto);
  }

  @GetMapping
  public Page<UsuarioResponseDTO> listar(
      @RequestParam(required = false) String nome,
      @RequestParam(required = false) String email,
      Pageable pageable) {
    return service.listar(nome, email, pageable);
  }

  @GetMapping("/{id}")
  public Usuario buscarPorId(@PathVariable Long id) {
    return service.buscarPorId(id);
  }

  @PutMapping("/{id}")
  public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
    return service.atualizar(id, dto);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable Long id) {
    service.deletar(id);
  }
}
