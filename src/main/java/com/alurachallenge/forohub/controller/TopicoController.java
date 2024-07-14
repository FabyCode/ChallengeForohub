package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.topic.*;
import com.alurachallenge.forohub.infra.errors.ValidacionIntegridad;
import com.alurachallenge.forohub.repository.TopicRepository;
import com.alurachallenge.forohub.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/topico")
@SecurityRequirement(name="bearer-key")
public class TopicoController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicService topicService;


    @PostMapping("/topico")
    @Transactional
    public ResponseEntity topicoRegistrado(@RequestBody @Valid TopicDTO topicDTO) throws ValidacionIntegridad {
        var topicoRegistrado = topicService.topicoCreado(topicDTO);
        return ResponseEntity.ok(topicoRegistrado);
    }

    @GetMapping("/topicos")
    public ResponseEntity<Page<ListarTopicsDTO>>  listarTopicos(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(topicRepository.findByActiveTrue(paged).map(ListarTopicsDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity topicUpdate (@RequestBody @Valid TopicUpdateDTO topicUpdateDTO){
        Topic topico= topicRepository.getReferenceById(topicUpdateDTO.id());
        topico.topicUpdate(topicUpdateDTO);
        return ResponseEntity.ok(new RespuestaTopicDTO(topico.getId(),
                topico.getTitle(),topico.getMessage(),
                topico.getStatus(),topico.getAuthor().getId(),
                topico.getCourse(),topico.getDate()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topic topico= topicRepository.getReferenceById(id);
        topico.diactivateTopic();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <RespuestaTopicDTO> respuestaTopico(@PathVariable Long id){
        Topic topico = topicRepository.getReferenceById(id);
        var topicoId = new RespuestaTopicDTO(topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                topico.getStatus(),
                topico.getAuthor().getId(),
                topico.getCourse(),
                topico.getDate());
        return ResponseEntity.ok(topicoId);
    }

}
