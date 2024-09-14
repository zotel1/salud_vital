package com.demo.salud_vital.infra.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyConfigurationCors {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String allowedOrigins = System.getenv("CORS_ALLOWED_ORIGINS");

                if (allowedOrigins == null || allowedOrigins.isEmpty()) {
                    allowedOrigins = "*"; // Permitir todos los orígenes si no está configurado
                }

                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS") // Asegúrate de incluir OPTIONS
                        .allowedHeaders("*")
                        .allowCredentials(true); // Si necesitas permitir credenciales
            }
        };
    }
}
