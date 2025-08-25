# Desafio Técnico Backend Júnior - JTech

## API RESTful para Gerenciamento de Tarefas

### Contextualização e Objetivo

A **JTech** busca identificar profissionais que demonstrem sólido conhecimento nos fundamentos do desenvolvimento backend. Este desafio técnico foi elaborado para avaliar suas competências na construção de APIs RESTful utilizando Java e Spring Boot.

**Objetivo:** Desenvolver uma API completa para gerenciamento de tarefas (TODO List), aplicando boas práticas de desenvolvimento, arquitetura limpa e documentação técnica de qualidade.

## Especificações Técnicas

### Requisitos Funcionais

1. **Criar Tarefa**: Endpoint `POST /tasks` para adicionar uma nova tarefa. A tarefa deve conter título, descrição e status (ex: "pendente", "concluída").
2. **Listar Tarefas**: Endpoint `GET /tasks` para retornar todas as tarefas cadastradas.
3. **Buscar Tarefa por ID**: Endpoint `GET /tasks/{id}` para obter os detalhes de uma tarefa específica.
4. **Atualizar Tarefa**: Endpoint `PUT /tasks/{id}` para atualizar o título, a descrição ou o status de uma tarefa.
5. **Deletar Tarefa**: Endpoint `DELETE /tasks/{id}` para remover uma tarefa do sistema.

### Requisitos Não Funcionais

1. **Persistência de Dados**: As tarefas devem ser armazenadas em banco de dados. Recomenda-se H2 (em memória) para simplificação ou PostgreSQL para demonstrar conhecimento em bancos relacionais.
2. **Validação de Dados**: Implementar validação robusta das entradas do usuário (ex: título da tarefa obrigatório e não vazio).
3. **Tratamento de Erros**: A API deve retornar códigos de status HTTP apropriados e mensagens de erro claras (ex: 404 para tarefa não encontrada, 400 para dados inválidos).

### Stack Tecnológica Obrigatória

* **Linguagem**: Java
* **Framework**: Spring Boot
* **Persistência**: Spring Data JPA com Hibernate
* **Banco de Dados**: H2 (em memória) ou PostgreSQL
* **Testes**: Testes unitários com JUnit/Mockito.

## Critérios de Avaliação

* **Qualidade e Organização do Código**: Código limpo, legível e seguindo as convenções do Java.
* **Aplicação de Boas Práticas**: Utilização de princípios como Clean Code e KISS.
* **Funcionalidade**: Todos os endpoints devem funcionar conforme especificado.
* **Testes Automatizados**: Cobertura de testes unitários para as classes de serviço e controllers.
* **Uso Adequado da Stack**: Configuração correta do Spring Boot, JPA e do banco de dados.
* **Modelagem de Dados**: Estrutura da entidade `Task` bem definida.
* **Controle de Versão**: Commits claros e lógicos no Git.

## Expectativa de Entrega

* **Prazo**: Até 3 dias corridos a partir do recebimento.
* **Formato**: Entregar o código-fonte em um repositório Git, acompanhado de um `README.md` completo.

### Estrutura Obrigatória do `README.md`

1. **Visão Geral do Projeto**: Breve descrição da API e seus objetivos.
2. **Stack Utilizada**: Lista das tecnologias implementadas.
3. **Como Rodar Localmente**: Instruções para configurar o ambiente, instalar dependências e iniciar o servidor.
4. **Como Rodar os Testes**: Comando para executar os testes.
5. **Estrutura de Pastas**: Explicação da organização do projeto.
6. **Decisões Técnicas**: Justificativas para as escolhas feitas (ex: por que usou H2 em vez de PostgreSQL).
7. **Melhorias Futuras**: Sugestões para evoluir a API.

---

**Boa sorte! A JTech está ansiosa para conhecer sua solução.**
