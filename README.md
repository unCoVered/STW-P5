# STW-P5
Practica5 de la asignatura Sistemas y Tecnologías Web del Grado en Ingeniería Informática de la Universidad de Zaragoza

Consiste en tres partes:
- Descarga desde servidor web local de un fichero XML -> Parseo de XML -> introducción de datos en base de datos local MySQL
- Extracción de datos de la base de datos local MySQL -> Creación de fichero JSON con esos datos -> Subida al servidor web local
- Descarga desde servidor web local del fichero JSON -> Parseo de JSON -> Creación de fichero HTML que muestre los datos del fichero. 


Programas y tecnologías usadas: 
- Tomcat para el ServidorWeb
- MySQL para la base de datos local
- IntelliJ como IDE

Librerías destacables:
- EclipseLink para gestión de JPA -> Eclipse.persistence y Eclipse.persistence.metamodels para gestión de entidades
- Javax.persistence para hacer uso de Entity manager, etc. 
- MySQL Connector
- GSON para parseo de JSON
- JSOM para parseo de XML
- Commons.IO de Apache para gestion de los ficheros al descargar y subir del servidor web local. 
