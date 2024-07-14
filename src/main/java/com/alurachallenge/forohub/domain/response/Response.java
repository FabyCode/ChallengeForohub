package com.alurachallenge.forohub.domain.response;

import com.alurachallenge.forohub.domain.topic.Topic;
import com.alurachallenge.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name="Response")
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private String solution;
    @OneToOne
    @JoinColumn(name="author", referencedColumnName="id")
    private User author;
    @OneToOne
    @JoinColumn(name="topic", referencedColumnName="id")
    private Topic topic;
    private boolean active;

    public Response(Long id, String solution, User usuario, Topic topico, LocalDateTime creationDate) {
        this.id=id;
        this.solution=solution;
        this.author=usuario;
        this.topic =topico;
        this.creationDate=LocalDateTime.now();
    }

    public void respuestaActualizada(ResponseUpdateDTO respuestaActualizadaDTO) {
        if (respuestaActualizadaDTO.solution() != null){
            this.solution=respuestaActualizadaDTO.solution();
        }
    }
    public void diactivateResponse(){

        this.active=false;
    }
}
