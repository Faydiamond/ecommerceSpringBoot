package com.panvdev.apirest_prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("http://localhost:2009","http://localhost:4209")  // Permite solicitudes desde localhost:2009 (puedes cambiarlo a la URL de tu frontend)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")  // Asegúrate de permitir el método OPTIONS
        .allowedHeaders("*")  // Permite cualquier encabezado
        .allowCredentials(true);  
    }
}