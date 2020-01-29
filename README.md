# Teste-api

Serviço responsável por cadastar endereços. É possível criar, alterar, recuperar e excluir um endereço.

O projeto foi documentado via Swagger:

Url: http://localhost:8090/swagger-ui.html

**Subindo a aplicação**

-Subir via maven: mvn spring-boot:run

-Via Docker:

    mvn clean install
    docker build -t teste-api:latest .
    docker run --name teste-api -p 8090:8090 teste-api:latest
    

