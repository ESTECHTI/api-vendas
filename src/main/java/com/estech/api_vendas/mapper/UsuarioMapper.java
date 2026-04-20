package com.estech.api_vendas.mapper;

import org.mapstruct.Mapper;

import com.estech.api_vendas.dto.UsuarioDTO;
import com.estech.api_vendas.dto.UsuarioResponseDTO;
import com.estech.api_vendas.entity.Usuario;

@Mapper(componentModel="spring")
public interface UsuarioMapper {
 
    Usuario toEntity(UsuarioDTO dto);

    UsuarioResponseDTO toResponse(Usuario usuario);
}
