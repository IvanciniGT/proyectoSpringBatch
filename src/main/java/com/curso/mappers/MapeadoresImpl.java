package com.curso.mappers;

import com.curso.entity.ItemDeEntrada;
import com.curso.entity.ItemProcesado;
import org.springframework.stereotype.Component;

@Component
public class MapeadoresImpl implements Mapeadores{

    @Override
         public ItemProcesado itemDeEntrada2ItemProcesado(ItemDeEntrada itemDeEntrada) {
            ItemProcesado itemProcesado = new ItemProcesado();
            itemProcesado.setNombreCompleto(itemDeEntrada.getNombre() + " " + itemDeEntrada.getApellidos());
            itemProcesado.setDni(itemDeEntrada.getDni());
            itemProcesado.setEmail(itemDeEntrada.getEmail());
            return itemProcesado;
        }
}
