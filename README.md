📚 Librería API - Spring Boot + MySQL

Proyecto desarrollado en Spring Boot que implementa un sistema CRUD para la gestión de libros en una librería.
Incluye operaciones con query params para realizar búsquedas personalizadas y se conecta a una base de datos MySQL.

---

🚀 Tecnologías utilizadas

Java 17+

Spring Boot

Spring Web

Spring Data JPA

MySQL

Lombok (opcional)

---

📦 Instalación y ejecución

1. Clonar el repositorio

git clone https://github.com/tu-usuario/libreria-springboot.git

cd libreria-springboot

2. Configurar la base de datos MySQL

Crear la base de datos en MySQL:

CREATE DATABASE libreria_db;


En el archivo src/main/resources/application.properties, configurar las credenciales de tu MySQL:

spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

3. Ejecutar el proyecto

Desde la raíz del proyecto:

mvn spring-boot:run


El servidor quedará disponible en:

http://localhost:8081


📖 Endpoints disponibles

CRUD de Libros

GET /api/libros → Listar todos los libros

GET /api/libros/{id} → Obtener un libro por ID

POST /api/libros → Crear un libro

PUT /api/libros/{id} → Actualizar un libro

DELETE /api/libros/{id} → Eliminar un libro

Búsquedas con Query Params

GET /api/libros/buscar/autor?autor=Gabriel → Buscar libros por autor

GET /api/libros/buscar/genero?genero=Novela → Buscar libros por género

GET /api/libros/buscar/precio?precio=20 → Buscar libros con precio menor a 20

GET /api/libros/buscar/stock?stock=10 → Buscar libros con stock mayor a 10

---

👨‍💻 Autor

Jorge Patricio Santamaría Cherrez

Máster en Ingeniería de Software y Sistemas Informáticos
