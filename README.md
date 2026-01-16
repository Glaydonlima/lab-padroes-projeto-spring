# Evolução de Padrões de Projeto com Spring Framework

Este projeto é uma evolução do laboratório inicial de Padrões de Projeto da DIO. Como estudante, dediquei um tempo considerável pesquisando como aplicar padrões mais avançados e práticos dentro do ecossistema Spring para tornar o projeto mais robusto, escalável e alinhado com as melhores práticas de mercado.

## Implementações e Melhorias feitas

Além dos padrões originais, implementei diversas evoluções baseadas em pesquisas sobre arquitetura de software:

## Como executar e testar
1. Execute o projeto via Maven ou pela IDE.
2. Acesse o Swagger em: `http://localhost:8080/swagger-ui.html`
3. Teste as operações de CRUD.
4. Acompanhe o console para ver os eventos do padrão Observer sendo disparados.

### 1. Chain of Responsibility
Usei esse padrão para organizar as validações ao criar ou atualizar um cliente.

- Em vez de concentrar várias validações com `if/else` dentro do service, cada regra ficou em uma classe específica (como validação de nome e CEP).
- Isso facilita a leitura do código e permite adicionar novas validações sem bagunçar o service principal.
- **Pacote:** `service.validation`

### 2. Observer
Implementei o padrão Observer usando o próprio mecanismo de eventos do Spring (`ApplicationEventPublisher`).

- Quando um cliente é salvo, um evento é disparado e tratado por um listener.
- Dessa forma, ações secundárias (como logs ou notificações) ficam desacopladas da lógica principal.
- Podemos utilizar para integrar com um serviço de e-mail, log, etc.
- **Pacote:** `service.event`

### 3. Adapter
Criei uma interface intermediária para o serviço de busca de endereço por CEP.

- Atualmente, a implementação usa o ViaCEP, mas se for necessário trocar de API no futuro, basta criar outra implementação.
- Isso evita dependência direta de um serviço externo específico.
- **Pacotes:** `service.EnderecoServiceAdapter` e `service.impl.ViaCepAdapter`

### 4. DTO e Builder
Utilizei DTOs para evitar expor diretamente a entidade `Cliente` na API.

- Os DTOs ajudam a separar melhor as responsabilidades entre a camada de controle e a camada de domínio.
- Usei o `@Builder` do Lombok para facilitar a criação dos objetos.
- **Pacote:** `model.ClienteDTO`

### 5. Facade e Strategy
- O Controller continua funcionando como uma **Facade**, centralizando e simplificando o acesso às regras de negócio.
- O uso de interfaces nos services e repositórios mantém a ideia do padrão **Strategy**, permitindo trocar implementações com facilidade.

## 🛠️ Tecnologias Utilizadas
- **Java 11**
- **Spring Boot 2.5.4**
- **Spring Data JPA**
- **Spring Cloud OpenFeign**
- **H2 Database**
- **Lombok** (Adicionado para evitar código repetitivo)
- **OpenAPI/Swagger**

