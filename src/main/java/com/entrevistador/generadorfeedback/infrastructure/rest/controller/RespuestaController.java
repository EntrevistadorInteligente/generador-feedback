package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.ConfirmacionDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/v1/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaCreation respuestaCreation;


    @PostMapping(value = "/solicitudes-feedback/entrevistas/{idEntrevista}")
    public Mono<ResponseEntity<ConfirmacionDto>> crearSolicitudFeedback(
            @PathVariable String idEntrevista,
            @RequestBody List<RespuestaComentarioDto> procesoEntrevista) {
        return this.respuestaCreation.iniciarSolicitudFeedback(RespuestaDto.builder()
                        .idEntrevista(idEntrevista)
                        .procesoEntrevista(procesoEntrevista)
                        .build())
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                        .body(ConfirmacionDto.builder().valor("Solicitud Feedback generado con exito").build())));
    }
}
