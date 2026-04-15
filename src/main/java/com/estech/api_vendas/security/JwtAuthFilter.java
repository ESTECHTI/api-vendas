package com.estech.api_vendas.security;

import org.springframework.web.filter.OncePerRequestFilter;
import com.estech.api_vendas.security.JwtService;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    protected void doFilterInternal(
        HttpServeletRequest request,
        HttpServeletResponse response,
        FilterChain filterChain
    ) throws ServeletException, IOException {

        String authHeader = request.getHeader("Authoriation");

        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            email = jwtService.extrairEmail(token);
        }
    }
}
