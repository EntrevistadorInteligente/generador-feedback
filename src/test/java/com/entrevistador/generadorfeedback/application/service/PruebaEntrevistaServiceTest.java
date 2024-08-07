package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.domain.port.client.OrquestadorClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PruebaEntrevistaServiceTest {
    @InjectMocks
    private PruebaEntrevistaService pruebaEntrevistaService;
    @Mock
    private PruebaEntrevistaDao pruebaEntrevistaDao;
    @Mock
    private OrquestadorClient orquestadorClient;


    @BeforeEach
    public void setUp() {
        WebFluxProperties entrevistaPruebaPorperties = mock(WebFluxProperties.class);
        when(entrevistaPruebaPorperties.getLimitPreguntas()).thenReturn(10);
        ReflectionTestUtils.setField(pruebaEntrevistaService, "webFluxProperties", entrevistaPruebaPorperties);

    }

    @Test
    void shouldGetPreguntasWhenValidRequest() {
        FeedbackResponse soloPreguntaImp = new FeedbackResponse("", "", "", "");
        when(this.pruebaEntrevistaDao.getPreguntas(anyString(), anyInt())).thenReturn(Flux.just(soloPreguntaImp));
        when(this.pruebaEntrevistaDao.getPreguntas(anyString(), anyInt())).thenReturn(Flux.just(soloPreguntaImp));
        when(this.orquestadorClient.getIdEntrevista(anyString()))
                .thenReturn(Mono.just(new IdEntrevista(anyString())));


        Flux<FeedbackResponse> publisher = this.pruebaEntrevistaService.getPreguntas("perfil");

        StepVerifier
                .create(publisher)
                .expectNext(soloPreguntaImp)
                .verifyComplete();

        verify(this.pruebaEntrevistaDao, times(1)).getPreguntas(anyString(), anyInt());
    }


}