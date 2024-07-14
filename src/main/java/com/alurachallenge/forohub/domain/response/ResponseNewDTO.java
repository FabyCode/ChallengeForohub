package com.alurachallenge.forohub.domain.response;

import java.time.LocalDateTime;

public record ResponseNewDTO(
        Long id,
        String solution,
        Long user_Id,
        Long topic_Id,
        LocalDateTime creationDate
) {
    public ResponseNewDTO(Response rVerified) {
        this(rVerified.getId(),rVerified.getSolution(),rVerified.getAuthor().getId(),rVerified.getTopic().getId(),rVerified.getCreationDate());
    }
}
