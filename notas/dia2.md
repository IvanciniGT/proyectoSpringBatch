# Spring 

Framework de inversión de control en JAVA
Nos facilita el uso de patrones de inyección de dependencias

# Inyección de dependecias

El que una clase no cree sus propias instancias, sino que se las inyecten desde fuera.
Y esto lo hacíamos para cumplir con el Principio de la Inversión de Dependencias

# Principio de la Inversión de Dependencias

Los los módulos de más alto nivel no dependan de Implementaciones de los módulos de bajo nivel... sino de sus abstracciones (interfaces)

# Dentro de Spring:

## Solicitar una inyección de dependencias

- Solicitar parámetros dentro de una función que fuese invocada por Spring
- @Autowired en propiedades de clases 
  - Añadir parámetros directamente el el CONSTRUCTOR de una clase

## Configurar la inyección de dependencias

- Añadir la anotación @Component a clases que queramos que sean inyectadas
- @Bean en funciones dentro de clases marcadas con @Configuration

** Por defecto decíamos que Spring cuántas instancias crea de esas clases que inyecta? 1 UNICA -> Comportamiento tipo SINGLETON

---
# SpringBatch

Librería de Spring para el procesamiento de datos en BATCH.
Programas tipo: ETL: TEL TETL
- E: Extracción
- T: Transformación
- L: Carga


Fichero CSV ---> Extración ---> Transformación ---> Carga ---> en BBDD
                                - Filtrados
                                - Enriquecimiento
                                - Transformación
                                - Validaciones

## Componentes de SpringBatch

Nosotros no montamos una aplicación. Esa la monta Spring por nosotros.
Lo que vamos a hacer es Configurar esa aplicación:

- Job: Un programa BATCH completo
- Step: Un paso dentro de un programa BATCH. Cada Step está compuesto de:
  - ItemReader: Componente que lee los datos de entrada
  - ItemProcessor: Componente que procesa los datos de entrada
  - ItemWriter: Componente que escribe los datos de salida
- JobListener: Componente que escucha eventos de un Job
- StepListener: Componente que escucha eventos de un Step
- ItemReaderListener: Componente que escucha eventos de un ItemReader
- ItemProcessorListener: Componente que escucha eventos de un ItemProcessor
- ItemWriterListener: Componente que escucha eventos de un ItemWriter
- Entidades
- Repositorios de datos

---

# En nuestro ejemplo:

- Proceso que lee Items (Personas) de un fichero CSV
- Salida va a ir a una BBDD ? Si nos da tiempo 
- Tareas asociadas a algunos eventos
- Validaciones

---

# Maven

Herramienta de automatización para proyectos principalmente JAVA (Aunque permite usar otros lenguajes):
- Compilación
- Pruebas
- Empaquetado
- Mandar el proyecto a SonarQube

Para compilar... y hacer pruebas... nuestra aplicación necesita dependencias... y maven me las gestiona.

proyecto/
    src/
        main/
            java/           Meto el código de mi aplicación
            resources/      Meto los recursos de mi aplicación (ficheros de conf. archivos de datos...)
        test/
            java/           Meto el código de mis pruebas automáticas
            resources/      Meto los recursos de mis pruebas automáticas
    pom.xml Archivo de configuración de maven para mi proyecto
        Dar datos básicos del proyecto
        Dar las dependencias del proyecto
        Dar instrucciones para indicar a maven cómo: Compilar, hacer pruebas, empaquetar, mandar a SonarQube

Cuando creamos un proyecto, podemos empezar de CERO o podemos usar un arquetipo (plantilla) de maven
ECLIPSE: Skip archetype selection