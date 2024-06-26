package com.entrevistador.generadorfeedback;

import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WebFluxProperties.class)
public class GeneradorFeedbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneradorFeedbackApplication.class, args);
	}

}
