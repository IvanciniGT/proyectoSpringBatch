# JAVA

Está de capa caída.
A principios de los 2000, era el lenguaje del FUTURO !!!!

App web: JAVA
    Frontal: Js (Angular, Vue, React, Polymer...)
    Backend: JAVA <- Está muy fuerte: SPRING
App desktop: JAVA -> C++, C#, VB, ObjectiveC
App embebido: JAVA -> C, Python
App Android: JAVA -> Kotlin

La razón principal de la muerte (o casi) de JAVA fue la compra de Sun Microsystems por parte de Oracle

# Kotlin

IntelliJ -> JetBrains -> Android Studio
                |
                v
              Kotlin

Motivación de Kotlin: 
1º JAVA tiene una gran cantidad de cagadas dentro de su sintaxis.
2º Guerra entre Oracle y Google

# Spring

Framework de Inversión de Control para el desarrollo de aplicaciones en JAVA.
Tiene más de 200 librerías.

# SpringBatch

Librería de Spring para el procesamiento de datos en BATCH.

# Framework vs Librería

Un Framework suele tener (agrupar) un conjunto de librerías...
Pero además impone una METODOLOGIA de trabajo

# Inversión de control

Patrón de diseño por el cuál DEJAMOS DE ESCRIBIR/CONTROLAR el flujo de mi aplicación
para delegarlo en un FRAMEWORK.

Para qué? Para facilitar el uso de un patrón de Inyección de dependencias!

# Inyección de dependencias

Otro patrón de desarrollo de software mediante el cuál las clases dejan de crear instancias de objetos que necesitan
ya que le son suministradas.

Para qué? Para respetar el principio de Inversión de dependencias

# Inversión de dependencias
                                                               v
Principio de desarrollo de software (uno de los 5 grandes: SOLID)
mediante el cuál, nos aseguramos de que los módulos / componentes de alto nivel de un sistema
NO DEPENDAN de implementación de los módulos de más bajo nivel... sino de abstracciones/contratos (interfaces/API)

Para qué? Para conseguir un código más mantenible/evolucionable!

---

# Desarrollar una app de consola que me permita buscar palabras en un diccionario y mostrar los significados si existe

Componentes/Módulos -> REPOS GIT

1 - API Librería que me permita gestionar DICCIONARIOS
2 - Implementación de ese API:
    - Implementación que use ficheros de texto para guardar las palabras
    - Implementación que use tablas de BBDD para guardar las palabras
    - ...
3 - Interfaz gráfica (consola) -> WEB... Android... YO QUE SE !

## API

    paquete:    com.diccionario
    jar:        diccionario-api-v1.0.0.jar

    interface Diccionario {
        boolean existe(String palabra);
        Optional<List<String>> getSignificados(String palabra) ;
            Hasta Java 1.8... colaría... Desde JAVA1.8 ESTO ES UNA MIERDA DESCOMUNAL !!!!!
            Si pregunto por la palabra ARCHILOCOCO !!! qué devuelve?
            - una lista vacía   \ No son explicitas
            - null              /
                Al menos diferencia claramente los 2 casos potenciales de comportamiento de esta función.
            - Exception         - Generar una Exception es algo caro computacionalmente
                                - Nunca debería usar excepciones para controlar LOGICA 
                                + El comportamiento queda EXPLICITO EN LA SIGNATURA DE LA FUNCION (throws NoSuchWordException)
            SonarQube: Herramienta de calidad de código 
    }
    interface SuministradorDeDiccionarios {
        boolean tienesDiccionarioDe(String idioma);
        Optional<Diccionario> getDiccionario(String idioma);
    }

# Implementación de la librería: Usando ficheros para albergar los diccionarios

    paquete:    com.diccionario.ficheros
    jar:        diccionario-ficheros-v1.0.0.jar

    public class DiccionarioDesdeFicheros extends Diccionario{
        // CODIGO
    }

    public class SuministradorDeDiccionariosDesdeFicheros extends SuministradorDeDiccionarios{
        // CODIGO
    }

# Aplicación de consola

    // import com.diccionario.ficheros.SuministradorDeDiccionariosDesdeFicheros;
        // Y esta linea es la muerte del proyecto. YA LA HE JODIDO PERO BIEN !!!!
        // Nos acabamos de mear en el ppo de INVERSION DE DEPENDENCIAS
    import com.diccionario.SuministradorDeDiccionarios;
    import com.diccionario.Diccionarios;

    public class AppDeConsolaGuay {
        
        private final SuministradorDeDiccionarios miSuministrador;
        
        public AppDeConsolaGuay(SuministradorDeDiccionarios miSuministrador){ // Inyección de dependencias
            this.miSuministrador=miSuministrador;
        }

        void procesarPetición(String palabra, String idioma, ){ 
            // Haré cositas
            boolean existe = false;
            // SuministradorDeDiccionarios miSuministrador = new SuministradorDeDiccionariosDesdeFicheros();
            if(miSuministrador.tienesDiccionarioDe(idioma)){
                Diccionario miDiccionario = miSuministrador.getDiccionario(idioma).get();
                existe = miDiccionario.existe(palabra)
            }
            // Hago lo que sea con el "existe"
        }
    }

# Dependencias

    App de consola -> API diccionarios <- Implementación de la librería


## Quién crea la instancia? Spring. El framework de inversión de control

SuministradorDeDiccionarios miSuministrador = new SuministradorDeDiccionariosDesdeFicheros()
new AppDeConsolaGuay(miSuministrador);


---

# Imaginad que queremos montar un proceso BATCH de forma tradicional (Ni spring, ni springboot, ni springbatch.. ni NA'... a pelo!)

## Esto me parece la CARTA A LOS REYES MAGOS !

De alguna forma.. los REQUISITOS de mi aplicación.

Fichero CSV con datos de personas (nombre, email... dni...)
Y quiero:
- Leer el fichero
- Para cada persona meterla en una BBDD (en la tabla PERSONAS)
Y quiero que cuando el programa arranque ME MANDE UN CORREO
Ah... y quiero que cuando lea cada persona, que le valida el email
Ah... y quiero que cuando el programa acabe ME MANDE OTRO CORREO
Ah... espera.. y que revise los DNIs de la gente antes de meterlo a la BBDD
Ah... y tengo un campo fecha nacimiento... calcula la EDAD para guardarla!

## A ESCRIBIR CODIGO ... en un metalenguaje

PASO 1: ABRE FICHERO
PASO 2: MANDA EMAIL DE INICIO
PASO 3: Voy leyendo cada linea del fichero ( BUCLE )
    PASO 4: Valido Email (IF)
    PASO 5: Valido DNI (IF)
    PASO 6: Calculo edad
    PASO 7: A la BBDD
PASO 8: Cierro fichero
PASO 9: ENVIAR EMAIL

Es el programa que tengo que montar... Qué acabo de hacer ahí?
DEFINIR EL FLUJO DE MI APLICACIÓN: Lenguaje imperativo (FOR-EACH, IF...)

## A ESCRIBIR CODIGO usando un FRAMEWORK DE INVERSION DE CONTROL

Quiero tener en mi app un postprocesador de lineas de texto... que guarde el resultado del procesamiento en una BBDD
                            @ItemWriter
Quiero tener en mi aplicación: un lector de Ficheros CSV con datos de personas (nombre, email... dni...)
                            @ItemReader
Quiero tener en mi app un PROCESADOR DE LINEAS DEL FICHERO QUE:
                            @ItemProcessor
    Tenga una validación del DNI
    Tenga una validación del email
    Calcule edad
Quiero tener en mi aplicación un EVENTO de inicio: que mande un email
                            @ StepListener
Quiero tener en mi aplicación un EVENTO de fin: que mande un email

@Step
@Job


Al usar un framework de inversión de control, no escribo el flujo de mi aplicación... Ese le proporciona el Framework
Yo me limito a contarle ...

    @OYE.. que la aplicación es un proceso BATCH
    public class MiAppPersonitasyCSVsYDNIsEnBBDD {

        public static void main(String[] args){
            // Spring, ejecuta mi aplicación
            SpringApplication.run(MiAppPersonitasyCSVsYDNIsEnBBDD.class);
            // YA !
        }

    }

# Anotaciones de JAVA

Salen en JAVA 1.5
Son esa cosita que en el código ponemos después de una ARROBA 
    @override
    @deprecated

Quién procesa esas anotaciones? EL COMPILADOR DE JAVA
En ese momento (el de la compilación) el compilador podrá usar la información de la anotación para:
- Lanzar un warning: @deprecated
- Lanzar un error: @override
- Escribir código: Muchas anotaciones que nos da:
    Spring
        @Bean
        @Configuration
        @SpringBootApplication
            @Configuration
            @EnableAutoConfiguration
            @ComponentScan
    SpringBatch
        @EnableBAtchProcessing
        @Step
        @Job
        @ItemReader
        @ItemWriter
        @ItemProcessor
        @ItemListener
        @StepListener
        @JobListener
        @Scheduled


# Programación de Jobs:

Una de las cosas que hace SpringBatch es llevar su propia BBDD con el registro de todos los trabajos ejecutados y cúando se han ejecutado.
Posteriormente podremos pedir que los trabajos se programen en el tiempo
para que se ejecuten con cierta periodicidad
O en un a fecha concreta
