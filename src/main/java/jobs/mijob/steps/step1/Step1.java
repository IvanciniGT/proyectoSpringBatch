package jobs.mijob.steps.step1;

import entity.ItemDeEntrada;
import entity.ItemProcesado;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

public class Step1 {

    // Por algún sitio le tengo que asociar:
    // Un Lector
    // Un Procesador
    // Un Escritor
    public void funcionQueConfiguraElStep1(
            ItemReader<ItemDeEntrada> elLectorQueVoyAUsar,                          // Solicitar una Inyección de dependencias
            ItemProcessor<ItemDeEntrada, ItemProcesado> elProcesadorQueVoyAUsar,    // Solicitar una Inyección de dependencias
            ItemWriter<ItemProcesado> elEscritorQueVoyAUsar                         // Solicitar una Inyección de dependencias
    ){

    }
}
