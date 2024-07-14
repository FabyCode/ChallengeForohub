package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.domain.topic.RespuestaTopicDTO;
import com.alurachallenge.forohub.domain.topic.Topic;
import com.alurachallenge.forohub.domain.topic.TopicDTO;
import com.alurachallenge.forohub.infra.errors.ValidacionIntegridad;
import com.alurachallenge.forohub.repository.TopicRepository;
import com.alurachallenge.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    public RespuestaTopicDTO topicoCreado(TopicDTO topicoDTO){
        if (!userRepository.findById(topicoDTO.usuario_Id()).isPresent()){
            throw new ValidacionIntegridad("Este ID de usuario no está registrado en la base de datos.");
        }
        var title= topicoDTO.title();
        if (title != null && topicRepository.existsByTitleIgnoreCase(title)){
            throw new ValidacionIntegridad("Este título ya está presente en la base de datos. Por favor revise el tópico existente.");
        }
        String message = topicoDTO.message();
        if (message != null && topicRepository.existsByMessageIgnoreCase(message)){
            throw new ValidacionIntegridad("Este mensaje ya está presente en la base de datos. Por favor revise el tópico existente.");
        }
        var usuario = userRepository.findById(topicoDTO.usuario_Id()).get();
        var topicoId= new Topic(null,title,message,topicoDTO.date(),topicoDTO.status(),usuario,topicoDTO.curso());
        topicRepository.save(topicoId);
        return new RespuestaTopicDTO(topicoId);
    }
}
