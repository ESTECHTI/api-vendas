package com.estech.api_vendas.security;

import org.springframework.web.filter.OncePerRequestFilter;
import com.estech.api_vendas.security.JwtService;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
}
