# API de Gestión de Notas de Alumnos

Esta es una API creada como parte de un proyecto de práctica para el ciclo de Formación Profesional Superior. La API permite gestionar las notas de los alumnos de un curso mediante dos funcionalidades principales.

## Características

* Permite guardar las notas de los alumnos de un curso en una base de datos MySQL.
* Permite buscar y descargar un archivo Excel con las notas de una asignatura específica en una clase y año determinados.

## Requisitos

* Java Development Kit (JDK) 8 o superior.
* Maven 3.6.3 o superior.
* Docker (opcional, solo si se desea ejecutar la base de datos en un contenedor Docker).

## Configuración

1. Clona el repositorio de la API desde GitHub.

2. Asegúrate de tener el JDK y Maven instalados en tu sistema.

3. Configura los parámetros de conexión a la base de datos MySQL en el archivo application.properties. Puedes encontrarlo en la siguiente ruta: src/main/resources/application.properties. Asegúrate de tener los siguientes parámetros correctamente configurados:

```
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_base_de_datos
spring.datasource.username=nombre_usuario
spring.datasource.password=contraseña_usuario
```
Si deseas ejecutar la base de datos en un contenedor Docker, consulta el apartado "Ejecución con Docker" más abajo.

4. Ejecuta el siguiente comando en la raíz del proyecto para compilar y construir el archivo JAR:
```
mvn clean package
```
Esto generará un archivo nombre_proyecto.jar en la carpeta target.

5. Ejecuta el archivo JAR con el siguiente comando:
```
java -jar nombre_proyecto.jar
```
La API se ejecutará en http://localhost:8080.
## Ejecución con Docker

1. Si deseas ejecutar la base de datos MySQL en un contenedor Docker, sigue los siguientes pasos:

2. Asegúrate de tener Docker instalado en tu sistema.
   ´´´
   docker run -p 3306:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=contraseña_root -e MYSQL_DATABASE=nombre_base_de_datos -d mysql:latest
   ´´´
Asegúrate de reemplazar contraseña_root y nombre_base_de_datos con tus propias configuraciones.

3. Configura los parámetros de conexión a la base de datos en el archivo application.properties de la misma manera que se mencionó anteriormente.

Con la base de datos MySQL en ejecución dentro del contenedor Docker, puedes seguir los pasos de la sección "Configuración" para compilar y ejecutar la API.
Ejecuta el siguiente comando para iniciar un contenedor MySQL:
## Contribución

Si deseas contribuir a este proyecto, siéntete libre de hacer un fork del repositorio y enviar tus pull requests. Serán revisados y considerados para su incorporación.

# Autor

Este proyecto fue desarrollado por Ivan Garcia Garcia.

## Licencia
Este proyecto está licenciado bajo los términos de la Licencia MIT. Consulta el archivo LICENSE para obtener más información.
