package com.alurachallenge.forohub.domain.user;


public record ListarUsersDTO(Long id,
                             String name,
                             String email
){
    public ListarUsersDTO(User user){

        this(user.getId(),user.getName(),user.getEmail());
    }
}
