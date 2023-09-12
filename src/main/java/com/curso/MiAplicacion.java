package com.curso;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Me evita las anotaciones de abajo
// Aqui le explico a Spring cómo es mi aplicación que quiero ejecutar:
// Mi aplicacion tiene configuraciones
//@EnableAutoConfiguration // Búscalas por los subpaquetes de este paquete
// Mi aplicacion tiene componentes
//@ComponentScan(basePackages = "com.curso.jobs") // Búscalos por los subpaquetes de este paquete
// Esta aplicacion utiliza SpringBatch.. por lo tanto busca Jobs
@EnableBatchProcessing
public class MiAplicacion {
    public static void main(String[] args){
        SpringApplication.run(MiAplicacion.class); // Inversión de control
    }
}
