package com.curso.jobs.mijob.steps.step1.listeners;

import com.curso.entity.ItemProcesado;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Si alguien pide un ItemWriteListener<ItemProcesado>
// le voy a dar una instancia de esta clase
public class EscritorListener implements ItemWriteListener<ItemProcesado> {
    @Override
    public void beforeWrite(List<? extends ItemProcesado> list) {
        System.out.println("Antes de escribir: "+list);
    }

    @Override
    public void afterWrite(List<? extends ItemProcesado> list) {
        System.out.println("Después de escribir: "+list);
    }

    @Override
    public void onWriteError(Exception e, List<? extends ItemProcesado> list) {
        System.out.println("Error al escribir: "+list);
        e.printStackTrace();
    }
}
