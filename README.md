
# TallerRestApiGym

## 1. Descripción del proyecto

Este proyecto consiste en una API REST desarrollada con Java y Spring Boot para administrar clientes y rutinas de entrenamiento de un gimnasio.

La aplicación permite registrar, consultar, actualizar y eliminar información mediante los métodos HTTP GET, POST, PUT y DELETE.

También cuenta con una relación muchos a muchos (Many-to-Many) entre clientes y rutinas, y documentación de los endpoints utilizando Swagger.

## 2. Dependencias utilizadas

Para desarrollar el proyecto utilicé las siguientes dependencias:

- **Spring Boot Data JPA:** permite trabajar con las entidades y realizar operaciones con la base de datos.
- **Spring Boot Validation:** permite validar los datos recibidos mediante anotaciones como `@NotBlank`, `@NotNull` y `@Pattern`.
- **Spring Boot WebMVC:** permite crear los controladores y gestionar las solicitudes HTTP.
- **SpringDoc OpenAPI:** genera la documentación de la API.
- **MySQL Connector:** permite conectar la aplicación con la base de datos MySQL.

También se utilizaron las siguientes herramientas:
- Java 17.
- Maven.
- MySQL.
- Hibernate.
- Swagger UI.

## 3. Configuración de la base de datos

Para utilizar el proyecto es necesario tener MySQL instalado y crear la siguiente base de datos:

```sql
CREATE DATABASE taller_rest_api_gym;
```

La configuración de conexión se encuentra en el archivo `application.properties`, ubicado en:

`src/main/resources/application.properties`

Se deben configurar las siguientes propiedades:

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/taller_rest_api_gym
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

Es necesario reemplazar `TU_CONTRASEÑA` por la contraseña del usuario de MySQL.

## 4. URL de Swagger

La documentación de la API se encuentra disponible mediante Swagger UI, donde se pueden visualizar y probar los endpoints de clientes y rutinas.

**Swagger UI:**

http://localhost:8080/swagger-ui/index.html

**OpenAPI JSON:**

http://localhost:8080/api-docs

Para acceder a la documentación es necesario que el proyecto se encuentre ejecutándose.

## 5. Instrucciones para ejecutar el proyecto

1. Clonar o descargar el repositorio Git.
2. Abrir el proyecto `TallerRestApiGym` desde NetBeans.
3. Crear la base de datos `taller_rest_api_gym` en MySQL.
4. Configurar el usuario y la contraseña de MySQL en el archivo `application.properties`.
5. Ejecutar **Clean and Build** desde NetBeans.
6. Ejecutar la clase principal `TallerRestApiGymApplication.java`.
7. Esperar a que Spring Boot inicie correctamente.
8. Abrir el navegador e ingresar a:

   http://localhost:8080/swagger-ui/index.html

Desde Swagger se pueden consultar y probar los endpoints disponibles.

## 6. Videos de evidencia

En el siguiente enlace se encuentra la carpeta de Google Drive con los videos de evidencia del proyecto:

[Ver videos de evidencia - Google Drive](https://drive.google.com/drive/folders/1ytbmGYsEuJpsb2uOYonA4ZN9x64LTDm3?usp=sharing)
