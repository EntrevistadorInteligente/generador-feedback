package com.entrevistador.generadorfeedback.infrastructure.beanconfiguration;

import com.entrevistador.generadorfeedback.infrastructure.adapter.converter.ModelConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}