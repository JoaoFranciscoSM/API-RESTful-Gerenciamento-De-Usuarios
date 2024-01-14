# API - Gerenciamento De Usuários 

## Descrição
Este repositório abriga uma API Java baseada em Spring Boot que oferece operações CRUD (Create, Read, Update, Delete) para gerenciar dados de usuários. Utilizando um banco de dados em memória, essa aplicação proporciona funcionalidades para interação e administração de informações de usuários, permitindo criar, ler, atualizar e deletar registros temporários em um banco de dados armazenado na memória durante o ciclo de vida da aplicação.

## Funcionalidades Principais
- Criação, leitura, atualização e remoção de informações dos usuários no banco de dados em memória.
  
## Tecnologias Utilizadas
- Java
- Spring Boot
- Lombok
- H2 Database
- Spring Data JPA
- RESTful API
- Maven

## Estrutura da Aplicação
Este projeto segue uma estrutura padrão para uma API RESTful com o framework Spring Boot:

#### Controller
As classes de controle, implementadas como RestControllers, definem os endpoints para realizar operações CRUD nos recursos de usuários. Cada endpoint corresponde a uma operação específica (CREATE, READ, UPDATE, DELETE).

#### Service
As classes de serviço são responsáveis pela lógica de negócios da aplicação. Elas interagem com o repositório para manipular os dados, validam informações e executam operações específicas sobre os usuários.

#### Repository
Os repositórios, utilizados em conjunto com o Spring Data JPA, fornecem métodos para acessar e manipular os dados no banco em memória. Eles mapeiam objetos Java para entidades do banco de dados temporário.

## Arquitetura e Padrões
Este projeto segue os princípios de uma API RESTful, utilizando os métodos HTTP adequados para cada operação e retornando códigos de status apropriados. Os dados são enviados e recebidos em formato JSON, seguindo as melhores práticas de design de APIs.
