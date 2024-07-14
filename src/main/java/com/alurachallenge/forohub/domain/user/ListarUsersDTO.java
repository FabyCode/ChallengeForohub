package com.alurachallenge.forohub.domain.user;

import org.springframework.security.core.userdetails.User;

public record ListarUsersDTO(Long id,
                             String name,
                             String email
){
    public ListarUsuariosDTO(User usuario){

        this(usuario.getId(),usuario.getName(),usuario.getEmail());
    }
}
