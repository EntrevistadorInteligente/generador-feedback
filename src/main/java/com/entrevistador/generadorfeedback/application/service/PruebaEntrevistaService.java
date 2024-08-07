package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.domain.port.client.OrquestadorClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PruebaEntrevistaService implements PruebaEntrevista {

    private final PruebaEntrevistaDao pruebaEntrevistaDao;
    private final WebFluxProperties webFluxProperties;
    private final OrquestadorClient orquestadorClient;

    @Override
    public Flux<FeedbackResponse> getPreguntas(String perfil) {
        return orquestadorClient.getIdEntrevista(perfil)
                .flatMapMany(idEntrevista -> pruebaEntrevistaDao.getPreguntas(idEntrevista.getId(), webFluxProperties.getLimitPreguntas()));
    }

}
