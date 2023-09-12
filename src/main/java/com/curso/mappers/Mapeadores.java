package com.curso.mappers;

import com.curso.entity.ItemDeEntrada;
import com.curso.entity.ItemProcesado;

public interface Mapeadores {
    ItemProcesado itemDeEntrada2ItemProcesado(ItemDeEntrada itemDeEntrada);
}
