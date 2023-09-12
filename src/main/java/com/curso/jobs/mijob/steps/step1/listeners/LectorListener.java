package com.curso.jobs.mijob.steps.step1.listeners;

import com.curso.entity.ItemDeEntrada;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component // Si alguien pide un ItemReadListener<ItemDeEntrada>
// le voy a dar una instancia de esta clase
public class LectorListener implements ItemReadListener<ItemDeEntrada> {

    @Override
    public void beforeRead() {
        System.out.println("Antes de leer");
    }

    @Override
    public void afterRead(ItemDeEntrada itemDeEntrada) {
        System.out.println("Después de leer el item: " + itemDeEntrada);
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("Error al leer");
        e.printStackTrace();
    }
}
