package jobs.mijob.steps.step1;

import entity.ItemProcesado;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

// Cuando alguien pida una instancia de un ItemWriter<ItemProcesado> le voy a dar una instancia de esta clase
@Component
public class Escritor implements ItemWriter<ItemProcesado> {
    @Override
    public void write(List<? extends ItemProcesado> items) throws Exception {
        /* Antes de Java 1.5
        for(int i=0; i<items.size(); i++){
            System.out.println(items.get(i));
        }
        */
        /* Antes de Java 1.8
        for(ItemProcesado item: items){
            System.out.println(item);
        }
        */
        // Java 1.8
        // Consumer<Object> impresor = System.out::println;
        // items.forEach(impresor); // Aplico la función println dentro de System.out a cada uno de los elementos de la lista
        items.forEach(System.out::println);
    }
}
