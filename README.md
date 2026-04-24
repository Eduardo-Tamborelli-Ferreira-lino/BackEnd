# ☕ BackEnd — Portfólio de Projetos Java

> Repositório centralizado com todos os projetos, estudos, atividades e provas de desenvolvimento Back-end desenvolvidos durante minha formação técnica, com foco em Java, JDBC, padrões de projeto e testes unitários.

---

## 📋 Sobre o Repositório

Este repositório reúne **toda a minha jornada de aprendizado em desenvolvimento Back-end com Java**, desde sistemas simples com JDBC puro até projetos mais complexos utilizando padrões de projeto (Strategy, Repository, DAO), injeção de dependências e testes unitários com JUnit 5. Cada subpasta é um projeto independente e autocontido.

---

## 🗂️ Estrutura de Pastas

```
BackEnd/
├── CyberPunk Garage/            # Sistema de garagem (Spring Boot + testes)
├── BibliotecaJDBC/              # Sistema de biblioteca com JDBC
├── Biblioteca_Aula_Testes/      # Biblioteca para testes de aula (padrão Repository)
├── contatos/                    # CRUD de contatos com JDBC
├── Estudo-2-JDBC/               # Estudo JDBC — CRUD de produtos
├── E_commerce-JDBC/             # Sistema de e-commerce com JDBC
├── gestao-ctw/                  # Sistema de gestão acadêmica (padrões Strategy)
├── PrimeiraAtividadeTeste/      # Primeira atividade com testes (Docker + JDBC)
├── Prova-MI77/                  # Prova prática — Sistema de manutenção de máquinas
├── SegundaAtividadeTeste/       # Segunda atividade com testes
├── Sistema-de-Gestao-de-Biblioteca/ # Sistema de biblioteca (Spring Boot + testes)
├── Sistema-De-Gestao-Em-JDBC/   # Sistema de gestão em JDBC puro
└── SistemaDeGestaoBiblioteca/   # Versão anterior da biblioteca (sem framework)
```

---

## 🚀 Projetos em Destaque

### 🔧 CyberPunk Garage — Sistema de Gestão de Garagem
> `CyberPunk Garage/`

Sistema completo de gestão de uma garagem mecânica cyberpunk com Spring Boot, testes unitários e banco de dados.

- **Tecnologias:** Java, Spring Boot, JDBC, JUnit 5, Docker
- **Padrões:** MVC, Repository Pattern, Injeção de Dependência
- **Destaques:**
  - Gestão de veículos, mecânicos e ordens de serviço
  - Testes unitários e de integração completos
  - Docker Compose para banco de dados PostgreSQL
  - Separação de camadas: `domain`, `infra`, `service`, `repository`
- **Arquivos-chave:** `pom.xml`, `docker-compose.yml`, `src/main/`, `src/test/`

---

### 🎓 gestao-ctw — Sistema de Gestão Acadêmica
> `gestao-ctw/`

Sistema acadêmico desenvolvido para gestão de alunos, turmas, professores e alocações.

- **Tecnologias:** Java, JUnit 5, Maven
- **Padrões:** Strategy Pattern, Repository Pattern, DTO
- **Destaques:**
  - Dois tipos de alocação: Padrão e Prioritária (`IAlocacaoStrategy`)
  - Dois tipos de notificação: E-mail e SMS (`INotificacaoStrategy`)
  - DTOs para transferência segura de dados
  - Cobertura de testes completa (domínio, serviço, estratégias, repositório)
  - Integração com sistema legado (`SistemaLegado.java`)
- **Estrutura:**
  ```
  domain/     → Aluno, Professor, Sala, Turma, interfaces
  dto/        → AlunoDTO, TurmaDTO
  infra/      → Implementações concretas das estratégias
  legado/     → Adapter para sistema legado
  service/    → GestaoAcademicaService
  ```

---

### 📚 Sistema de Gestão de Biblioteca (Spring Boot)
> `Sistema-de-Gestao-de-Biblioteca/`

Sistema completo de gestão de biblioteca com Spring Boot, utilizando boas práticas de arquitetura em camadas.

- **Tecnologias:** Java, Spring Boot, JDBC/JPA, JUnit 5, Docker
- **Padrões:** Repository, Service Layer, DAO
- **Funcionalidades:** Cadastro de livros, autores, empréstimos e usuários
- **Destaques:** Testes unitários, Docker Compose para banco, separação clara de camadas

---

### 🛒 E-commerce JDBC
> `E_commerce-JDBC/`

Sistema de e-commerce construído com JDBC puro, sem frameworks de persistência.

- **Tecnologias:** Java, JDBC, Maven, Docker
- **Destaques:**
  - Conexão direta ao banco via JDBC (`docker-compose.yml` incluído)
  - Operações de CRUD completas para produtos, clientes e pedidos
  - Gerenciamento de transações manual

---

### 🏥 Prova MI77 — Sistema de Manutenção de Máquinas
> `Prova-MI77/`

Prova prática que implementa um sistema de gestão de manutenção industrial.

- **Tecnologias:** Java, JDBC, Maven, Docker
- **Entidades:**
  - `Maquinas` — Cadastro de máquinas industriais
  - `Tecnicos` — Técnicos de manutenção
  - `OrdemManutencao` — Ordens de serviço de manutenção
  - `Pecas` — Controle de peças utilizadas
  - `OrdemPeca` — Relação entre ordens e peças
- **Enums:** `statusManutencao`, `statusMaquina`
- **Estrutura DAO:** Uma classe DAO por entidade

---

### 📇 Contatos — CRUD com JDBC
> `contatos/`

Sistema simples de CRUD para gerenciamento de contatos usando JDBC.

- **Tecnologias:** Java, JDBC, Maven
- **Funcionalidades:** Criar, listar, atualizar e deletar contatos no banco de dados
- **Ideal para:** Referência de padrão DAO com JDBC

---

### 📦 Estudo-2-JDBC — CRUD de Produtos
> `Estudo-2-JDBC/`

Estudo prático de JDBC realizando operações de CRUD sobre uma entidade `Produto`.

- **Tecnologias:** Java, JDBC, Maven
- **Estrutura:**
  - `Conexao.java` — Gerenciamento de conexão com o banco
  - `Produto.java` — Entidade produto
  - `ProdutoDao.java` — Data Access Object para produtos
  - `Main.java` — Demonstração das operações

---

### 📖 BibliotecaJDBC
> `BibliotecaJDBC/`

Sistema de biblioteca com JDBC puro — gerencia livros, usuários e empréstimos.

- **Tecnologias:** Java, JDBC, Maven
- **Entidades:** `Livro`, `Emprestimo`
- **DAOs:** `LivroDao`, `EmprestimoDao`
- **Funcionalidades:** Cadastro de livros, registro de empréstimos e devoluções

---

### 🏫 Biblioteca — Aula de Testes
> `Biblioteca_Aula_Testes/`

Projeto de biblioteca desenvolvido durante aula prática com foco em aplicação de testes unitários no padrão Repository.

- **Tecnologias:** Java, JDBC, Maven, Docker
- **Padrões:** Repository Pattern, Service Layer
- **Interfaces:** `LivroRepository`, `UsuarioRepository`, `EmprestimoRepository`
- **Implementações:** `LivroRepositoryImpl`, `UsuarioRepositoryImpl`, `EmprestimoRepositoryImpl`
- **Serviços:** `LivroService`, `UsuarioService`, `EmprestimoService`

---

### 🗃️ Sistema De Gestão Em JDBC
> `Sistema-De-Gestao-Em-JDBC/`

Sistema de gestão completo utilizando JDBC puro com Spring Boot e Docker.

- **Tecnologias:** Java, Spring Boot, JDBC, Maven, Docker
- **Destaques:** Estrutura organizada em camadas, conexão via JDBC sem JPA

---

### 📝 SistemaDeGestaoBiblioteca
> `SistemaDeGestaoBiblioteca/`

Versão anterior da biblioteca desenvolvida sem framework, com JDBC puro e estrutura de pacotes manual.

- **Tecnologias:** Java, JDBC
- **Estrutura:**
  ```
  Connection/   → Conexao.java
  DAO/          → AutorDAO.java, LivroDAO.java
  DTO/          → ListarLivrosDTO.java
  Main/         → Main.java
  Model/        → Autor.java, Livro.java
  ```

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white) | 17+ | Linguagem principal de todos os projetos |
| ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white) | 3.x | Gerenciamento de dependências e build |
| ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) | 3.x | Framework para projetos mais avançados |
| ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat&logo=postgresql&logoColor=white) | 14+ | Banco de dados relacional |
| ![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=flat&logo=docker&logoColor=white) | — | Containerização do banco de dados |
| ![JUnit](https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=junit5&logoColor=white) | 5 | Testes unitários e de integração |

---

## 📐 Padrões de Projeto Aplicados

| Padrão | Projetos |
|--------|----------|
| **DAO (Data Access Object)** | BibliotecaJDBC, E_commerce-JDBC, Prova-MI77, contatos |
| **Repository Pattern** | PrimeiraAtividadeTeste, Sistema-de-Gestao-de-Biblioteca, Biblioteca_Aula_Testes |
| **Strategy Pattern** | gestao-ctw (Alocação + Notificação) |
| **DTO (Data Transfer Object)** | gestao-ctw, SistemaDeGestaoBiblioteca |
| **Service Layer** | gestao-ctw, Sistema-de-Gestao-de-Biblioteca, CyberPunk Garage |
| **Adapter** | gestao-ctw (integração com sistema legado) |

---

## 📈 Evolução do Aprendizado

```
Java Básico (OOP) → JDBC puro (DAO) → Padrão Repository
         ↓
Docker + Banco de Dados → Maven Build
         ↓
Padrões de Projeto (Strategy, DTO, Service Layer)
         ↓
Spring Boot + Injeção de Dependências → Testes Unitários (JUnit 5)
```

---

## ⚙️ Como Executar os Projetos

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Docker & Docker Compose (para projetos com banco)
- IntelliJ IDEA (recomendado)

### Passos Gerais

```bash
# 1. Navegue para o projeto desejado
cd BackEnd/BibliotecaJDBC

# 2. Suba o banco de dados (se houver docker-compose.yml)
docker-compose up -d

# 3. Execute via Maven
mvn clean install
mvn exec:java

# Ou importe no IntelliJ IDEA e execute a classe Main
```

---

## 👨‍💻 Autor

**Eduardo Tamborelli Ferreira Lino**
Estudante de Desenvolvimento de Software — SENAI

[![GitHub](https://img.shields.io/badge/GitHub-Eduardo--Tamborelli--Ferreira--lino-181717?style=flat&logo=github)](https://github.com/Eduardo-Tamborelli-Ferreira-lino)
