
# Spring

    public class MiClase {

        // Y quiero que Spring se encargue de darme valor para esta propiedad
        private TipoDato miDato;

    }


## Solicitar una Inyección de dependencias

### Opción 1: Simplemente pidiendo el dato en una función que Spring ejecute / invoque

    public class MiClase {
        public void miFuncion(TipoDato unDatoQueNecesito){
            // Mi código
        }
    }

    Si Spring es quién invoca a esa función... Spring me hace la inyección de dependencias sin necesidad de yo poner ningún tipo de información/anotación adicional.

    Este es un caso muy peculiar... para que funcione tengo que asegurarme que Spring será quién llame a mi función.
    Ya veremos cómo me aseguro de eso.

### Opción 2: Haciendo uso de la anotación @Autowired

    Si Spring es quién crea instancias de mi clase, mirará si hay alguna propiedad anotada con la anotación @Autowired
    Y si es así, Spring suministrará un valor para esa propiedad.

    public class MiClase {
        @Autowired
        private TipoDato miDato;
    }

    public class MiAppGuayDeConsolaYDiccionarios{
        @Autowired
        private SuministradorDeDiccionarios miSuministrador;
    }

    Lamentablemente tengo que contaros que ESTO a día de hoy está considerado una MALA PRACTICA !!!!
    - Si os fijáis.. cómo está declarada la propiedad? PRIVADA
        Y entonces... desde fuera de este código me la pueden asignar? A priori no... 
            pero java tiene sus truquitos: REFLECTIONS ... pero esto es LENTO ! a nivel computacional
    - Esa propiedad se rellena por Spring después de haberse ejecutado el constructor.
        Por tanto, dentro del constructor no puedo hacer uso de esa propiedad.

    Debe haber una alternativa:

### Alternativa: Usar la Opción 1... para conseguir la opción 2... CON EL CONSTRUCTOR

    public class MiClase {
        private TipoDato miDato;
        public MiClase(TipoDato miDato){
            this.miDato = miDato;
        }
    }



## Configurar la inyección de dependencias

Una cosa es cómo dentro de mi código en una clase, pido a Spring que rellene una variable (propiedad de una clase)
Eso es la INYECCION DE DEPENDENCIAS... vease punto 1

Otra cosa es cómo indicar a Spring que valor debe meter ahí. Ésto es la CONFIGURACION DE LA INYECCION DE DEPENDENCIAS...
Que se explica en este punto.

### Opción 1: Usando la anotación @Component... o uno de sus subtipos

NOTA: Si esta opción es válida (usable) en mi caso de uso... NO MIRO MAS!!! ES LA BUENA !

```java
    public class MiClase {
        private TipoDato miDato;
        public MiClase(TipoDato miDato){ // Solicitar una INYECCION DE DEPENDENCIAS
            this.miDato = miDato;
        }
    }

    public interface TipoDato{
        // ... lo que sea aquí
    }

    @Component // @Service @ItemProcessor con estas funcionaría igual.. ya que @Service o @ItemProcessor son subtipos de @Component
    public class TipoDatoImplementacion implements TipoDato {
        // Implementamos las funciones
    }
```

Al anotar esa clase como componente, cuando alguien pida un TipoDato, Spring suministrará una instancia de 
TipoDatoImplementacion, ya que esta clase está anotada como @Component.

Diremos que @Component es una anotación básica de Spring.... pero hay un montón de anotaciones
que extienden a la anotación @Component: @Repository, @Service, @Controller, @Job, @ItemProcessor

Si hay más de una clase que implementa la interfaz, Spring devolverá una instancia de aquella que esté anotada con @Component
Si hay más de una clase con anotación @Component para la misma interfaz: ERROR DE COMPILACION !
    // Aquí habrá truquitos

```java
    @ComponentScan("com.curso.implementacion2"); // Esto es una GUARRADA !!!! Ya me ligo al paquete de la implementación
                                                 // Tendrá su caso de uso... especialmente en SpringBatch
    public class MiClase {
        private TipoDato miDato;
        public MiClase(TipoDato miDato){ // Solicitar una INYECCION DE DEPENDENCIAS
            this.miDato = miDato;
        }
    }

    public interface TipoDato{
        // ... lo que sea aquí
    }

    package com.curso.implementacion1;
    @Component
    // @Qualifier("Subtipo")
    public class TipoDatoImplementacion1 implements TipoDato {
        // Implementamos las funciones
    }

    package com.curso.implementacion2;
    @Component
    public class TipoDatoImplementacion2 implements TipoDato {
        // Implementamos las funciones
    }
```

Habrá otros truquitos mejores.

### OPCION 2: Declarando un BEAN

NOTA: SOLO usar esta opción si no puedo usar la opción 1.
Si la opción 1 me vale.. Dejo de leer este fichero.

Pregunta: ... y cuándo entonces NO PUEDO usar la OPCION 1?
Si no tengo acceso al código de esa clase... porque no es mia.. es de una librería...



```java
    public class MiClase {
        private TipoDato miDato;
        public MiClase(TipoDato miDato){ // Solicitar una INYECCION DE DEPENDENCIAS
            this.miDato = miDato;
        }
    }

    // ESTO ES EXTERNO A MI ... NO TENGO ACCESO AL CODIGO
        // Este tipo de dato (interfaz) no lo defino yo... está definido en una librería externa
        public interface TipoDato{
            // ... lo que sea aquí
        }
        // En esa misma librería... o en otra... han definido una implementación de la interfaz que quiero que Spring inyecte cuando 
        // Una función mia pida un dato de ese tipo.
        public class TipoDatoImplementacion implements TipoDato {
            // Implementamos las funciones
        }
    // Error de compilación... Usted está pidiendo un dato de tipo TipoDato... y no tienen ninguna clase con la anotación @Component

    // Para que Spring entienda que esta clase tiene Anotaciones @Bean... y efectivamente ejecute esas funciones,
    // La clase en si misma debe llevar la anocación @Configuration
    @Configuration
    public class MiClaseDeConfiguracion {
        @Bean // Por esta anotada con la etiqueta @Bean, Spring llamará a esta función.
              // Cacheará el resultado de la llamada
              // Cuando alguien pida un dato de tipo (el que devuelva esta función): TipoDato, 
              // le inyectará lo que haya devuelto esta función (cuando se ejecutó... que el resultado se cacheó)
        public TipoDato proverTipoDato(  ){
            // Aqui devuelvo un objeto adecuado
            return new TipoDatoImplementacion();
        }
        @Bean 
        public TipoDato proverTipoDato( TipoParametro1 param1, TipoParametro2 param2 ){
            return new TipoDatoImplementacion();
        }   // En un caso como este, ya que Spring es quién va a ejecutar esa función... spring también hará Inyección de dependencias..
            // Y empiezo con inyecciones de dependencias en CASCADA !
    }


    // EJEMPLO REAL SpringBatch
    public class EstapaDeUnProcesoBatch {
        public void definirEtapa( ItemReader<Persona> lectorPersonas ){
            // lectorPersonas.read()
            // Y haré cosas con los datos..
        }
    }
    // Adicionalmente , SpringBatch me da un MONTON DE IMPLEMENTACIONES DE Readers:
    // CSVFileItemReader
    @Configuration
    public class LectorDePersonasConfiguration{
        @Bean
        public ItemReader<Persona> dameElItemReaderDelFicheroDePersonasDeMiAplicacion(){
            return new CSVFileItemReader().setDelimiter(";");
        }
        @Bean
        // Quiero un reader que lea de una BBDD MariaDB
        public ItemReader<Persona> dameElItemReaderDelFicheroDePersonasDeMiAplicacion(){
            return new JdbcCursorItemReader().setDataSource(miDataSource);
        }
    }


    // * NOTA: A esta clase la podeis llamar como os venga en gana
    // * NOTA: A esta función la podeis llamar como en venga en gana
```

Otra NOTA... NOTAZA !!!!
!!! IMPORTANTISIMO DE NARICES !!!!!

Por defecto, ( y normalmente el deseado), cuando Spring crea una instancia de una clase anotada como @Component
o cuando Spring llama a una función anotada con @Bean,
El objeto es cacheado... y siempre se devuelve la misma instancia de ese objeto.
Si hay 50 sitios que piden un objeto de ese tipo... a todos se les da la misma instancia!

La clase que provee la implementación adquiere (sin necesidad de tener la lógica de programación) comportamiento de singleton:

```java
@Component
class TipoDatoImplementacion implements TipoDato{

    private static volatile TipoDatoImplementacion instancia = null; // Volatile desactiva la cache a nivel de Thread en la JVM... para evitar dar datos incorrectos

    private TipoDatoImplementacion(){
        // Mi código
    }
    public static getInstance(){
        if(instancia == null){          // Este me asegura que si ya hay una instancia, no se hace un bloqueo del hilo (synchronized)... que es un proceso caro
            synchronized(TipoDatoImplementacion.class){
                if(instancia == null){  // Este me asegura que solo se ejecuta el código de dentro 1 UNICA VEZ;
                    instancia = new TipoDatoImplementacion();
                }
            }
        }
        return instancia;
    }
    

}
```

## Configurar y arrancar Mi Aplicación

```java
    @SpringBootApplication
        //@ComponentScan("paquete donde están mis clases anotadas con @Component")
        //@EnableAutoConfiguration // Buscar clases con anotación @Configuration en este paquete o subpaquetes.
    // Lo que hace es que Spring Escanee en AUTOMATICO todas las carpetas por debajo de la carpeta donde tengo este fichero:
    // (Subpaquetes)
    // En busca de clases con anotación @Component o un subtipo
    // O en busca de clases con anotación @Configuration
    public class MiAppPersonitasyCSVsYDNIsEnBBDD {

        public static void main(String[] args){
            SpringApplication.run(MiAppPersonitasyCSVsYDNIsEnBBDD.class);
        }

    }
```

## Qué es Springboot?

Nos ofrece unas "plantillas"(starters) para el inicio rápido de un proyecto.
Os he dicho que Spring tiene más de 200 librerías...
Os imaginais lo que es configurar las dependencias de un proyecto?

Starters:
- Procesos Batch
- Servicio REST
- Conectar con un Kafka