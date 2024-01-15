# Autenticação API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

Este projeto é uma API construída usando **Java, Java Spring, PostgresSQL e Spring Security com JWT para controle de autenticação.**

A API foi desenvolvida como teste de programação da TIVIA

## Índice

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)
- [Contributing](#contributing)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/abrantessistemas/demo.git
```

2. Instale dependências com Maven

3. instale [PostgresSQL](https://www.postgresql.org/)

## Uso

1. Inicie o aplicativo com Maven
2. A API estará acessível em http://localhost:8080


## API Endpoints
A API fornece os seguintes endpoints:

```markdown
CRUD para usuarios - recupera uma lista de usuários (é necessário acesso de ADMIN)

CRUD para beneficiarios - Registre um novo beneficiário (é necessário acesso de ADMIN).

CRUD para documentos - Registre um novo documento para um beneficiário (é necessário acesso de ADMIN).

POST auth/login - Faça login no aplicativo.

POST auth/register - Cadastre um novo usuário no aplicativo
```

## Autenticação
A API usa Spring Security para controle de autenticação. As seguintes funções estão disponíveis:

```
USUÁRIO -> Função de usuário padrão para usuários logados.
ADMIN -> Função administrativa para parceiros gestores (registo de novos parceiros).
```
Para acessar endpoints protegidos como usuário ADMIN, forneça as credenciais de autenticação apropriadas no cabeçalho da solicitação.

## Database
O projeto utiliza [PostgresSQL](https://www.postgresql.org/) como banco de dados.

## Contribuindo

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, abra um problema ou envie uma solicitação pull ao repositório.

Ao contribuir para este projeto, siga o estilo de código existente, [convenções de commit](https://www.conventionalcommits.org/en/v1.0.0/), e envie suas alterações em um branch separado.




