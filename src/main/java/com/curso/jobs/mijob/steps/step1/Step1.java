package com.curso.jobs.mijob.steps.step1;

import com.curso.entity.ItemDeEntrada;
import com.curso.entity.ItemProcesado;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Cuando alguien pida un Step, que se devuelva una instancia de esta clase
public class Step1 {

    // Por algún sitio le tengo que asociar:
    // Un Lector
    // Un Procesador
    // Un Escritor
    @Bean
    public Step configurarElStep1(
            StepBuilderFactory factoriaDeSteps,                                     // Solicitar una Inyección de dependencias
            ItemReader<ItemDeEntrada> elLectorQueVoyAUsar,                          // Solicitar una Inyección de dependencias
            ItemProcessor<ItemDeEntrada, ItemProcesado> elProcesadorQueVoyAUsar,    // Solicitar una Inyección de dependencias
            ItemWriter<ItemProcesado> elEscritorQueVoyAUsar,                        // Solicitar una Inyección de dependencias
            ItemWriteListener<ItemProcesado> elListenerQueVoyAUsar ,                 // Solicitar una Inyección de dependencias
            ItemReadListener<ItemDeEntrada> elListener2QueVoyAUsar                   // Solicitar una Inyección de dependencias
    ){
        // Los Steps dentro de SpringBatch no los creamos nosotros.
        // SpringBatch me ofrece una clase llamada StepBuilderFactory que me permite crear Steps
        return factoriaDeSteps.get("step1") // Quiero un paso llamado Step1
                .<ItemDeEntrada, ItemProcesado>chunk(10) // El tamaño de transacción y tipos de datos
                .listener(elListenerQueVoyAUsar)        // Le digo el listener que voy a usar
                .listener(elListener2QueVoyAUsar)       // Le digo el listener que voy a usar
                .reader(elLectorQueVoyAUsar)            // Le digo que lector voy a usar
                .processor(elProcesadorQueVoyAUsar)     // Le digo que procesador voy a usar
                .writer(elEscritorQueVoyAUsar)          // Le digo que escritor voy a usar
                // SpringBatch es el que dará orden y concierto a ésto.
                .build();                               // Construye el Step
    }
}
