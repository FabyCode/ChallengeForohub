package com.alurachallenge.forohub.domain.response;

import java.time.LocalDateTime;

public record ListarResponsesDTO(
        Long id,
        String solution,
        Long user_Id,
        Long topic_Id,
        LocalDateTime creationDate
) {
    public ListarResponsesDTO(Response response){
        this(response.getId(),
                response.getSolution(),
                response.getAuthor().getId(),
                response.getTopic().getId(),
                response.getCreationDate());
    }
}
