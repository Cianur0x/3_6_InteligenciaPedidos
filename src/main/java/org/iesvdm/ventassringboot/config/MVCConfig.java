package org.iesvdm.ventassringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

// Indica que esta clase es una clase de configuración de Spring
@Configuration
// Especifica el paquete base que Spring debe escanear en busca de componentes gestionados por Spring, como controladores, servicios y repositorios
@ComponentScan(basePackages = "org.iesvdm.ventassringboot.config")
public class MVCConfig implements WebMvcConfigurer { // Esta clase configura aspectos específicos de Spring MVC

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();

        // Establecer el idioma predeterminado como español de España
        slr.setDefaultLocale(new Locale("es", "ES"));
        return slr;
    }

    /**
     * Este interceptor permite cambiar dinámicamente el idioma de la aplicación en función de un parámetro de solicitud específico
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();

        // Establecer el nombre del parámetro que se utilizará para cambiar el idioma
        lci.setParamName("lang");
        return lci;
    }

    // Añadir el interceptor al registro de interceptores
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
