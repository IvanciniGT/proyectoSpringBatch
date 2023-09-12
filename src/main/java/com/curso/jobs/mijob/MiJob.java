package com.curso.jobs.mijob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Cuando alguien pida un Job, quiero que se le devuelva éste
public class MiJob {

    @Bean
    public Job functionQueConfiguraMiJob(
            JobRepository repositorioDeJobs, // BBDD donde se guardan los datos de los com.curso.jobs que se han ejecutado
            JobBuilderFactory jobBuilderFactory,
            Step unPasoDelJob
    ){
        // Aquí defino el flujo de los Steps
        return jobBuilderFactory.get("miJob")// Quiero crear un job que se llama MiJob
                .repository(repositorioDeJobs) // BBDD donde se guardan los datos de los com.curso.jobs que se han ejecutado
                .flow(unPasoDelJob) // El job empieza con el Step unPasoDelJob
                .end() // El job termina aquí
                .build(); // Construir el job
    }
}
