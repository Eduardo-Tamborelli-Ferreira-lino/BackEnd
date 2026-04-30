# 🏎️ CyberPunk Garage

Sistema completo de gestão de uma garagem mecânica cyberpunk desenvolvido com **Spring Boot**. O foco do projeto é a aplicação de padrões de arquitetura modernos, separação de camadas e testes rigorosos.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **JDBC** (Persistência)
- **JUnit 5** & **Mockito** (Testes)
- **Docker** & **PostgreSQL** (Infraestrutura)
- **Maven** (Gerenciamento de dependências)

## 🏗️ Arquitetura do Projeto
O sistema segue uma arquitetura em camadas para facilitar a manutenção e testabilidade:
- `domain`: Entidades de negócio e interfaces de repositório.
- `infra`: Implementações concretas de repositórios e configurações de banco.
- `service`: Regras de negócio e orquestração de processos.
- `dto`: Objetos de transferência de dados para entrada e saída.

## 🚀 Funcionalidades
- Cadastro e gestão de **Veículos**.
- Cadastro e gestão de **Mecânicos**.
- Abertura e acompanhamento de **Ordens de Serviço**.
- Fluxo completo de manutenção com validações de segurança.

## ⚙️ Como Executar
1. Certifique-se de ter o Docker instalado.
2. Na raiz do projeto, execute:
   ```bash
   docker-compose up -d
   ```
3. Execute o build do Maven:
   ```bash
   mvn clean install
   ```
4. Inicie a aplicação via sua IDE preferida ou via terminal:
   ```bash
   mvn spring-boot:run
   ```

---
Desenvolvido por **Eduardo Tamborelli Ferreira Lino**
