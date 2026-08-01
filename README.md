# ecommerce-api

Monorepo com os microsserviços de um e-commerce, construídos com Spring Boot 4 e Java 21.

## Estrutura

O projeto é dividido em três módulos Maven independentes (cada um com seu próprio `pom.xml`):

| Módulo | Artifact ID | Pacote base | Responsabilidade |
|---|---|---|---|
| [products/](products/) | `produtos` | `shopping.ecommerce.products` | Catálogo de produtos |
| [customers/](customers/) | `clientes` | `shopping.ecommerce.customers` | Cadastro de clientes |
| [services/](services/) | `servicos` | `shopping.ecommerce.services` | Pedidos / regras de negócio |

Cada módulo é uma aplicação Spring Boot standalone, sem dependência de build entre eles.

## Stack

- Java 21
- Spring Boot 4.1.0 (Web MVC, Data JPA)
- PostgreSQL
- Maven (com wrapper `mvnw`)
- Lombok

## Banco de dados

O schema (`services/database/schema.sql`) define três bancos, um por domínio: `shoppingproducts`, `shoppingcustoomers` e `shoppingorders`.

Para subir um PostgreSQL local via Docker:

```bash
cd services/database
docker compose up -d
```

Isso sobe o Postgres na porta `5555` com usuário/senha `postgres`/`postgres`. O volume de dados fica em `services/database/data/` (ignorado pelo git).

Depois de subir o container, aplique o `schema.sql` manualmente nas bases criadas.

## Rodando um módulo

Cada módulo é independente e roda na porta padrão `8080`. Para executar mais de um simultaneamente, configure `server.port` em `src/main/resources/application.properties`.

```bash
cd products   # ou customers / services
./mvnw spring-boot:run
```

## Status

Projeto em estágio inicial: os módulos ainda são esqueletos Spring Boot (sem controllers/entidades implementados). A modelagem de dados já existe em `services/database/schema.sql`.
