# Desafio Técnico Fullstack Júnior - JTech

## Aplicação TODO List Completa (Frontend + Backend)

### Contextualização e Objetivo

A **JTech** busca identificar desenvolvedores versáteis capazes de construir aplicações web completas, demonstrando competência tanto no desenvolvimento backend quanto frontend. Este desafio avalia sua capacidade de integração entre tecnologias e entrega de soluções end-to-end.

**Objetivo:** Desenvolver uma aplicação TODO List completa, integrando um backend robusto com um frontend interativo, demonstrando domínio em toda a stack de desenvolvimento web moderna.

## Especificações Técnicas

### Backend - API RESTful

1. **Endpoints Obrigatórios** para gerenciamento de tarefas:
   * `POST /tasks`: Criar nova tarefa
   * `GET /tasks`: Listar todas as tarefas
   * `PUT /tasks/{id}`: Atualizar tarefa existente (título, descrição, status)
   * `DELETE /tasks/{id}`: Remover tarefa
2. **Persistência de Dados**: Implementar armazenamento em banco de dados (H2 em memória ou PostgreSQL).
3. **Validação Robusta**: Validar todas as entradas de dados (ex: título obrigatório e não vazio).
4. **Tratamento de Erros**: Retornar códigos HTTP adequados e mensagens de erro estruturadas.

### Frontend - Interface Interativa

1. **Interface Completa** para consumo da API:
   * Exibição organizada da lista de tarefas
   * Formulário para adição de novas tarefas
   * Funcionalidade de edição inline ou modal
   * Sistema de remoção com confirmação
   * Toggle para marcar/desmarcar como concluída
2. **Arquitetura de Componentes**: Organização modular em componentes Vue reutilizáveis.
3. **Design Responsivo**: Interface adaptável para diferentes dispositivos e tamanhos de tela.

### Stack Tecnológica Obrigatória

**Backend:**

* Linguagem: Java
* Framework: Spring Boot
* Persistência: Spring Data JPA com Hibernate
* Banco de Dados: H2 (em memória) ou PostgreSQL
* Testes: Testes unitários (JUnit/Mockito)

**Frontend:**

* Framework: Vue 3 (Composition API)
* Gerenciamento de Estado: Pinia
* Estilização: Material Design (Vuetify ou biblioteca similar)
* Testes: Testes unitários (Vitest)

## Critérios de Avaliação

* **Integração Frontend-Backend**: Comunicação eficiente e correta entre cliente e servidor.
* **Qualidade e Organização do Código**: Código limpo e bem estruturado em ambas as camadas.
* **Aplicação de Boas Práticas**: Implementação de princípios Clean Code e KISS.
* **Funcionalidade Completa**: Todos os requisitos funcionais implementados e integrados.
* **Testes Automatizados**: Cobertura de testes unitários para componentes críticos do backend e frontend.
* **Uso Adequado da Stack**: Utilização correta e eficiente de todas as tecnologias propostas.
* **Experiência do Usuário**: Interface intuitiva com feedback adequado para ações do usuário.

## Expectativa de Entrega

* **Prazo**: Até 3 dias corridos a partir do recebimento.
* **Formato**: Repositório Git único contendo pastas `frontend` e `backend`, com `README.md` na raiz.

### Estrutura Obrigatória do `README.md`

1. **Visão Geral do Projeto**: Descrição completa da aplicação e arquitetura.
2. **Stack Utilizada**: Lista detalhada das tecnologias backend e frontend.
3. **Como Rodar Localmente**: Instruções passo a passo para inicializar backend e frontend.
4. **Como Rodar os Testes**: Comandos para executar testes de ambas as camadas.
5. **Estrutura de Pastas**: Explicação da organização do projeto completo.
6. **Decisões Técnicas**: Justificativas para escolhas arquiteturais e tecnológicas.
7. **Melhorias Futuras**: Propostas para evolução da aplicação.

---

**Boa sorte! A JTech está ansiosa para conhecer sua solução fullstack.**
