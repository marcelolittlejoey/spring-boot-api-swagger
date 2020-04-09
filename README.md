# Spring-boot - API - Swagger

Service responsable to register address. It's possible to create, update, find and delete an address.

API RESTful

API docummentation by Swagger. 

**Running application**

-By maven: mvn spring-boot:run

-By Docker:

    mvn clean install
    docker build -t teste-api:latest .
    docker run --name teste-api -p 8090:8090 teste-api:latest

Swagger-UI: http://localhost:8090/swagger-ui.html    

