package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.service.PruebaEntrevistaService;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/muestra")
@RequiredArgsConstructor
public class EntrevistaPruebaController {
    private final PruebaEntrevistaService interviewTestService;

    @GetMapping(path = "/preguntas")
    public Flux<FeedbackResponseDto> obtenerPreguntas(@RequestParam("perfil") String perfil){
        return this.interviewTestService.getPreguntas(perfil);
    }

}
