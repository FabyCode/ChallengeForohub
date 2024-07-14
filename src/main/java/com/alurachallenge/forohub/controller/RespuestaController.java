package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.response.*;
import com.alurachallenge.forohub.infra.errors.ValidacionIntegridad;
import com.alurachallenge.forohub.repository.ResponseRepository;
import com.alurachallenge.forohub.repository.TopicRepository;
import com.alurachallenge.forohub.repository.UserRepository;
import com.alurachallenge.forohub.service.ResponseService;
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
@RequestMapping("/respuesta")
@SecurityRequirement(name="bearer-key")
public class RespuestaController {
    @Autowired
    private ResponseService responseService;
    @Autowired
    private ResponseRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity respuestaRegistrada (@RequestBody @Valid ResponseDTO respuestaDTO) throws ValidacionIntegridad {
        var respuestaRegistrada = responseService.respuestaCreadaDTO(respuestaDTO);
        return ResponseEntity.ok(respuestaRegistrada);
    }

    @GetMapping("/respuestas")
    public ResponseEntity<Page<ListarResponsesDTO>>  listarRespuestas(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(repository.findByActiveTrue(paged).map(ListarResponsesDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity respuestaActualizada(@RequestBody @Valid ResponseUpdateDTO respuestaActualizadaDTO){
        Response respuesta=repository.getReferenceById(respuestaActualizadaDTO.id());
        respuesta.respuestaActualizada(respuestaActualizadaDTO);
        return ResponseEntity.ok(new ResponseNewDTO(respuesta.getId(),respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopic().getId(),
                respuesta.getCreationDate()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Response respuesta = repository.getReferenceById(id);
        respuesta.diactivateResponse();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <ResponseNewDTO> respuestaCreada(@PathVariable Long id){
        Response respuesta=repository.getReferenceById(id);
        var respuestaRegistrada = new ResponseNewDTO(respuesta.getId(),
                respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopic().getId(),
                respuesta.getCreationDate());
        return ResponseEntity.ok(respuestaRegistrada);
    }
}
