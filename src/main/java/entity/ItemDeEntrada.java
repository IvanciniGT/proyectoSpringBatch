package entity;

import lombok.Data;

@Data
// Genera los getters, setters, toString, equals, hashCode y un constructor vacío
public class ItemDeEntrada {
    // Defino lo que voy a leer del fichero CSV
    // Esta clase tiene estructura de POJO (Plain Old Java Object)-> DTO (Data Transfer Object)
    // Es decir, es una clase que solo tiene atributos y getters y setters
    private String nombre;
    private String apellidos;
    private String email;
    private String dni;
}
