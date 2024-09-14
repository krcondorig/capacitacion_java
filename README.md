# Capacitacion java

Este proyecto es una aplicacion Java para hacer consultas de pacientes de un hospital.

## Tecnologias utilizadas

✅ Java JDK 17

✅ Spring Boot

✅ JUnit

✅ Mockito

✅ SQL Server 2014

✅ Maven

✅ Thymeleaf

✅ Swagger 

## ¿Qué puedes hacer?

![image](https://github.com/user-attachments/assets/6b003c65-9dfd-452c-9c0c-c3ebf806db6a)

- Buscar paciente por id

![image](https://github.com/user-attachments/assets/4ac9b418-6964-436c-aa5a-eade356ecb42)

- Listar todos los pacientes 

![image](https://github.com/user-attachments/assets/95629f43-259e-40ec-b850-176b1cd3ac52)

- Registrar paciente

![image](https://github.com/user-attachments/assets/8ed5ae45-87fb-41a5-8c5a-f003585d3cdf)

- Actualizar paciente

![image](https://github.com/user-attachments/assets/5724e99f-29c0-49c7-b613-0fa32faa54ac)

- Eliminar paciente

![image](https://github.com/user-attachments/assets/4fdfb17f-6ad6-4161-aded-67b9ff2e92cb)

- Listar pacientes con historial medico

![image](https://github.com/user-attachments/assets/66881d04-b12c-455d-944f-38885287e0cd)

## ¿Cómo empezar?

1. Clona el repositorio en tu maquina local

```
git clone https://github.com/krcondorig/capacitacion_java.git
```

2. Configura la base de datos

    -El archivo sql se encuentra en ``.src/main/resources/sql/bd_hospital.sql``

    -Abrir el archivo bd_hospital.sql con SQL Server 2014

    -Ejecutar el archivo bd_hospital.sql para crear la base de datos y las tablas con los datos.

    ![image](https://github.com/user-attachments/assets/e19030bf-b7bc-4eae-9b04-113dcf032eae)



3. Abre el proyecto en Spring tool suite 4
4. Instala las dependencias

    - Click derecho en el archivo ``pom.xml`` luego click en Run As luego click en Maven install

    ![image](https://github.com/user-attachments/assets/ae0255c2-0a30-4d85-9736-497dd659e28c)

5. Configura el archivo  ``.src/main/resources/application.properties``

![image](https://github.com/user-attachments/assets/3593e358-ee0e-4507-83c2-343d84d6bad5)


```
spring.datasource.url=jdbc:sqlserver://DEVICE_NAME;trustServerCertificate=true;databaseName=DB_NAME
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```
6. Ejecuta la aplicacion


## Vistas
- Listar todos los pacientes

```
http://localhost:8080/paciente/listar
```

![image](https://github.com/user-attachments/assets/b4cf952d-a1e7-497a-a65f-fe5690fdd16e)

- Listar todos los pacientes con historial medico

```
http://localhost:8080/paciente/consultarHistorial
```

![image](https://github.com/user-attachments/assets/f233d94e-0173-4149-b370-6aee2020017e)

- Mostrar paciente por id

```
http://localhost:8080/paciente/1
```

![image](https://github.com/user-attachments/assets/075c4f12-a2b3-4cb6-917e-fc02aea884a4)


## Pruebas unitarias

![image](https://github.com/user-attachments/assets/d2365be2-0ec9-4753-9dff-f9a4ab673695)

## Preview del Swagger

```
http://localhost:8080/swagger-ui/index.html
```

![image](https://github.com/user-attachments/assets/0978138c-76a6-425f-bf98-428ed8d30536)


## Por terminar
- Implementar el boton editar en la vista de lista de pacientes.
- Implementar el boton registrar paciente en la vista de la lista de pacientes.
