# 📚 Sistema de Gestão de Biblioteca (Spring Boot)

Sistema moderno de gestão de bibliotecas desenvolvido com **Spring Boot**, aplicando as melhores práticas de arquitetura em camadas e persistência de dados.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **JDBC / JPA**
- **Docker** (MySQL/PostgreSQL)
- **JUnit 5**
- **Maven**

## 🏗️ Arquitetura
O projeto utiliza uma estrutura clara de responsabilidades:
- **`controller`**: Endpoints para interação (se aplicável) ou interface de linha de comando.
- **`service`**: Camada de lógica de negócio (empréstimos, devoluções, multas).
- **`repository`**: Abstração da camada de dados utilizando o **Repository Pattern**.
- **`domain` / `model`**: Entidades centrais como `Livro`, `Autor`, `Usuario` e `Emprestimo`.

## 🚀 Principais Funcionalidades
- Cadastro completo de acervo (Livros e Autores).
- Gestão de usuários e histórico de atividades.
- Sistema de empréstimos com validação de disponibilidade.
- Relatórios de livros mais lidos e usuários ativos.

## ⚙️ Como Executar
1. Certifique-se de ter o Docker e Maven instalados.
2. Inicie o container do banco de dados:
   ```bash
   docker-compose up -d
   ```
3. Execute o build:
   ```bash
   mvn clean install
   ```
4. Rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```

---
Desenvolvido por **Eduardo Tamborelli Ferreira Lino**
