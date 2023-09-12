package com.curso.jobs.mijob.steps.step1;

import com.curso.entity.ItemDeEntrada;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

// En esta clase, defino el ItemReader que quiero tener asociado a mi Step1
@Configuration
public class Lector {

    // Esto es un regalito de Spring. Se tima del fichero application.properties.. GRATIS !!!!
    @Value("${fichero.entrada}")
    private String nombreFicheroEntrada;

    // SpringBatch define una Interfaz llamada ItemReader<T> que tiene un método read() que devuelve un objeto de tipo T.
    @Bean
    public ItemReader<ItemDeEntrada> getMiReader() {
        return new FlatFileItemReaderBuilder<ItemDeEntrada>()
                .name("miLector")
                .resource(new ClassPathResource(nombreFicheroEntrada))
                .delimited() // CSV
                .names("nombre","apellidos","email","dni")
                .targetType(ItemDeEntrada.class) // El tipo de objetos que genero
                .build();
    }
    // En esta clase:
    // Configuro Spring (@Configuration)
    // de forma que si alguien pide un "ItemReader<ItemDeEntrada>" le devuelvo lo que devuelve esta función
    // por haberla marcado con la anotación @Bean

    // Formación Java 1.8
    // Programación funcional
    // Paquete nuevo gestión de fechas: java.time (antiguo joda-time)
    // Optional
    // Meter código estático en interfaces

    // Java 9, 10 y 11
    // Java 9: Módulos de java - Proyecto Jigsaw
    //          Interfaz
    //          Clase
    //          Paquetes
    //          Módulos
    // Java 21

    // Formación de Spring

    // Docker & Contenedores
}
