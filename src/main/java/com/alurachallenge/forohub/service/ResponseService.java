package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.domain.response.Response;
import com.alurachallenge.forohub.domain.response.ResponseDTO;
import com.alurachallenge.forohub.domain.response.ResponseNewDTO;
import com.alurachallenge.forohub.infra.errors.ValidacionIntegridad;
import com.alurachallenge.forohub.repository.ResponseRepository;
import com.alurachallenge.forohub.repository.TopicRepository;
import com.alurachallenge.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponseRepository repository;

    public ResponseNewDTO respuestaCreadaDTO(ResponseDTO respuestaDTO) {
        if (!userRepository.findById(respuestaDTO.user_Id()).isPresent()){
            throw new ValidacionIntegridad("Este ID de usuario no está registrado en la base de datos. ");
        }
        if (!topicRepository.findById(respuestaDTO.topic_Id()).isPresent()){
            throw new ValidacionIntegridad("Este id de tópico no está registrado en la base de datos. ");
        }
        var usuario = userRepository.findById(respuestaDTO.user_Id()).get();
        var topico = topicRepository.findById(respuestaDTO.topic_Id()).get();

        var rVerified= new Response(null,respuestaDTO.solution(),usuario,topico,respuestaDTO.creationDate());
        repository.save(rVerified);
        return new ResponseNewDTO(rVerified);
    }
}
