# 🎬 CineManager

Sistema de gestão de cinema com foco em **transações críticas** utilizando JDBC puro.

## 🎯 Objetivo
Praticar a implementação manual de controle transacional (ACID) em Java, garantindo que operações complexas que envolvem múltiplas tabelas (como cancelamentos e reservas) sejam atômicas.

## 🛠️ Tecnologias
- **Java**
- **JDBC**
- **MySQL** (Docker)
- **Maven**

## 📐 Regras de Negócio Implementadas
- **Cancelamento Crítico:** Ao registrar um cancelamento por "DANO_AO_EQUIPAMENTO", o sistema desativa o cliente e coloca o filme em manutenção em uma única transação.
- **Validações de Reserva:** Impedir a exclusão de clientes com reservas futuras.
- **Relatórios:** Geração de relatórios de cancelamentos com multas elevadas utilizando Joins SQL.

## 📂 Estrutura
- `model`: Entidades (Cliente, Filme, Reserva, Cancelamento).
- `repository`: DAOs para persistência dos dados.
- `service`: `CinemaService` contendo as regras de negócio e controle de `commit`/`rollback`.

## 🚀 Como Executar
1. Suba o banco de dados:
   ```bash
   docker-compose up -d
   ```
2. Execute o projeto via Maven:
   ```bash
   mvn clean install
   mvn exec:java
   ```

---
Desenvolvido por **Eduardo Tamborelli Ferreira Lino**
