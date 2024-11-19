
# DummyJSON Client - Java 17 e Spring Boot 3.2.x (atualizado por MARCOS GONTIJO)

## Descrição do Projeto

Este projeto é um microsserviço Java que interage com a API pública [DummyJSON](https://dummyjson.com/docs/products) para realizar operações de busca de produtos. O projeto foi desenvolvido usando Java 8 e Spring Boot 2.6.x.

## Objetivo do Desafio

O desafio consiste em migrar este projeto para Java 17 e Spring Boot 3.2.5. Durante a migração, você enfrentará várias dificuldades, incluindo a adaptação ao novo namespace, substituição de métodos depreciados e ajustes em testes unitários.

## Funcionalidades

- **Consulta de Produtos**: Realiza chamadas para a API do DummyJSON para buscar informações sobre produtos.
- **Integração com `RestTemplate`**: Utiliza `RestTemplate` para realizar chamadas HTTP.
- **Validação de Dados**: Validação de dados de entrada usando Bean Validation (`javax.validation`).
- **Gestão de Dependências**: Configurado para utilizar @Autoweird.
- **Testes Unitários**: Inclui testes unitários desenvolvidos com JUnit 4 e Mockito.

## Estrutura do Projeto

```bash
dummyjson-client
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.dummyjsonclient
│   │   │       ├── DummyJsonClientApplication.java
│   │   │       ├── client
│   │   │       │   └── ProductClient.java
│   │   │       ├── controller
│   │   │       │   └── ProductController.java
│   │   │       ├── dto
│   │   │       │   └── Product.java
│   │   │       ├── service
│   │   │       │   └── ProductService.java
│   │   └── resources
│   │       └── application.yaml
│   └── test
│       ├── java
│       │   └── com.example.dummyjsonclient
│       │       ├── config
│       │       │   └── RestTemplateConfigTest.java
│       │       └── controller
│       │       │   └── ProductControllerTest.java
│       │       ├── dto
│       │       │   └── ProductTest.java
│       │       └── service
│       │           └── ProductServiceTest.java
│       └── resources
└── pom.xml
```

## Passos para Executar o Projeto

### Pré-requisitos

- **Java 8**
- **Maven 3.8.x**

### Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.ibm.com/Wendell-Santos/code-challenge-migration.git
    cd dummyjson-client
    ```

2. Compile e execute o projeto:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Acesse o serviço:

    O serviço estará disponível em `http://localhost:8080`.

### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```

## Requisitos de Entrega

1. Atualizar o `pom.xml` para usar Java 17+ e Spring Boot 3.2.5.
   - atualizado o solicitado (versao java e versao spring)(OK)
   - ajustado versão do plugin do pitest para uma versao compativel com java 17 para compilar (OK)
   - pesquisei sobre pitest e obtive esta informação
   (A versão 1.4.3 do Pitest não suporta o bytecode gerado pelo Java 17 (major version 61 ou superior). A versão 1.11.1 adiciona suporte ao Java 17.)
   - adicionado maven.compiler.target>17</maven.compiler.target> para especificar que o código gerado também deve estar no nível Java 17. Sem isso o maven compilar pode gerar bytecode incompativel dependendo das configuracoes da JVM.
   -Substituido dependências incompatíveis
      Algumas dependencias são incompatíveis ou obsoletas com o Spring Boot 3.x e o Java 17. Foi verificado e atualizado:
    - javax.validation foi substituído por jakarta.validation:
     -Substituido findbugs-maven-plugin    
     - O FindBugs foi substituído pelo SpotBugs.
     -Ao atualizar a versao do Spring encontrei divergencias na dependencia e no uso do javax em algumas classes pois A versao do SpringBoot 3.x é baseado em jakarta. Sendo assim o mesmo foi substituido por jakarta.
   

2. Substituir `RestTemplate` por `WebClient` ou `Openfeign`. - (OK)
3. Substituir os testes unitários feitos com `JUnit 4` e `Mockito` por testes de integração utilizando `@SpringBootTest`-  (OK)
4. Refatorar qualquer código depreciado ou incompatível.- (OK)
5. Garantir que todos os testes ainda passam após a migração. - (OK)
6. Deixar a URL da API dummyjson parametrizada por ambiente no projeto - (OK)
7. Adicionar no projeto um novo path `/health` que retorna a saude do microsserviço - (OK)

## Validação Sobre o Challenge

- O projeto deve estar funcionando em Java 17 e Spring Boot 3.2.5. (OK)
- Todos os testes unitários devem ser executados e passar sem falhas. (OK)
- O código deve estar devidamente documentado e organizado. (OK)

## Extras

- Entregar o projeto em container será um diferencial.

- 1) Após gerar o mvn install do projeto.
- 2) docker build -t code-challenge-marcosgontijo .
- 3) docker run -p 8000:8080 code-challenge-marcosgontijo

- Comandos utilizado para deixar o projeto em container do docker.
- Fica a critério do desenvolvedor inserir ou remover dependencias do projeto para garantir o objetivo do challenge.
