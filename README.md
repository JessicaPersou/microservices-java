# MICROSERVICES-JAVA

Este repositório contém o código do Livro: Back-end Java (Microsserviços, Spring Boot e Kubernetes)

## Microserviços da Aplicação

> **User-API**: Possui os serviços para gerenciar os usuários da aplicação.

> **Product-API**: Possui os serviços para gerenciar os produtos disponíveis para compras.

>  **Shopping-API**: Os serviços para que usuários realizem compras.

## Banco de dados

As aplicações criam as tabelas automaticamente quando são executadas pela primeira vez, porém o banco de dados deve ser criado no postgres.

As aplicações estão configuradas para se conectar ao banco de dados dev, por isso antes de rodar as aplicações, crie esse banco de dados. Se quiser alterar o nome do banco de dados, altere o arquivo application.properties de cada projeto. Utilizando o docker compose, esse banco de dados já é criado automaticamente.

Todos os projetos acessam o mesmo banco de dados, apenas criam schemas diferentes.

## Postman

O arquivo **`microservice-java.postman_collection.json`** é uma collection do Postman que possui as chamadas para os serviços da aplicação. A collection está configurada para chamar os serviços já no Kubernetes. Para chamar na execução local, basta trocar o shopping.com para localhost:808x.

## Execução

A maneira mais simples de executar a aplicação é utilizando o docker-compose, para isto, basta executar o comando: _`docker-compose up`_ depois que a imagem docker dos microserviços forem criadas.

## Versões

- Spring Boot 3.0.0
- Java 17.
