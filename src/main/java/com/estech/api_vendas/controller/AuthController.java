package com.estech.api_vendas.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estech.api_vendas.dto.LoginDTO;
import com.estech.api_vendas.entity.Usuario;
import com.estech.api_vendas.repository.UsuarioRepository;
import com.estech.api_vendas.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UsuarioRepository repository, PasswordEncoder passwordEncoder,
        JwtService jwtService) {
            this.repository = repository;
            this.passwordEncoder = passwordEncoder;
            this.jwtService = jwtService;
        }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        Usuario user = repository.findByEmail(dto.email())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (!passwordEncoder.matches(dto.senha(), user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(user.getEmail());
    }
 }
