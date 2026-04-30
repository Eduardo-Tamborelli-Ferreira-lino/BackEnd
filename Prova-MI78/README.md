# 🏭 Prova Prática MI78

Sistema de gestão de equipamentos e fornecedores desenvolvido para a avaliação técnica MI78. O projeto aplica o **padrão Repository** e separação clara entre camadas de serviço e infraestrutura.

## 🛠️ Tecnologias
- **Java**
- **JDBC**
- **Maven**
- **Padrão Repository**

## 🏗️ Estrutura do Projeto
- `model`: Entidades (Equipamento, Fornecedor).
- `repository`: Interfaces e implementações JDBC para persistência.
- `service`: Lógica de negócio e validações.
- `database`: Configuração de conexão.

## 🚀 Como Executar
1. Configure seu banco de dados local.
2. Compile o projeto:
   ```bash
   mvn clean install
   ```
3. Execute a classe `Main`.

---
Desenvolvido por **Eduardo Tamborelli Ferreira Lino**
