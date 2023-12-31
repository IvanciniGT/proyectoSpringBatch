package com.curso.jobs.mijob.steps.step1;

import com.curso.entity.ItemDeEntrada;
import com.curso.entity.ItemProcesado;
import com.curso.mappers.Mapeadores;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component // Yo soy quien ofrece la implementación de ItemProcessor<ItemDeEntrada, ItemProcesado>
public class Procesador implements ItemProcessor<ItemDeEntrada, ItemProcesado> {
    private final Mapeadores mapeador;
    public Procesador(Mapeadores mapeador) {
        this.mapeador = mapeador;
    }
    @Override
    public ItemProcesado process(ItemDeEntrada itemDeEntrada) throws Exception {
        // La lógica de procesamiento de datos... Que es muy particular mia...
        // Aquí no hay una clase mágica que ofrezca SpringBatch para esto.
        // Me toca montarlo yo.
        // Validar el DNI:
        if( ! itemDeEntrada.getDni().matches("^[0-9]{1,8}[A-Z]$") )
            return null; // Este item pasas de él... filtrado... no lo procesamos
                         // No llega al Writer
                         // Y está lógica la define SpringBatch
       // Validar el email contra una regex de "emails"
        if( ! itemDeEntrada.getEmail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$") )
            return null; // Filtras el item
        // Esto lo sacaría a una clase... que habitualmente recibe el nombre de: Mapper
        return mapeador.itemDeEntrada2ItemProcesado(itemDeEntrada);
    }
}
