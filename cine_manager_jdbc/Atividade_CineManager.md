# Atividade Prática: Sistema de Gestão de Cinema (CineManager)

## Contexto
Desta vez, vamos gerenciar um **Cinema**. O desafio subiu de nível: agora, além das regras de negócio e validações defensivas, você **deve implementar a lógica de transações** onde for necessário, garantindo a integridade dos dados.

## 1. Entidades do Sistema (Modelos)

*   **`Cliente`**
    *   `id` (Integer)
    *   `nome` (String)
    *   `email` (String)
    *   `ativo` (Boolean)
*   **`Filme`**
    *   `id` (Integer)
    *   `titulo` (String)
    *   `genero` (String)
    *   `status` (String - "EM_CARTAZ", "MANUTENCAO", "INDISPONIVEL")
*   **`Reserva`**
    *   `id` (Integer)
    *   `cliente_id` (Integer)
    *   `filme_id` (Integer)
    *   `data_reserva` (LocalDate)
    *   `data_sessao` (LocalDate)
*   **`Cancelamento`**
    *   `id` (Integer)
    *   `reserva_id` (Integer)
    *   `motivo` (String)
    *   `taxa_multa` (Double)

## 2. A Interface do Serviço (`CinemaService`)

### `Cancelamento registrarCancelamento(Cancelamento cancelamento)`
*   **Regra de Negócio (TRANSACIONAL):** Se o motivo do cancelamento for **"DANO_AO_EQUIPAMENTO"**, o sistema deve:
    1.  Desativar o `Cliente` (`ativo = false`).
    2.  Alterar o status do `Filme` daquela reserva para **"MANUTENCAO"**.
    3.  Salvar o registro de `Cancelamento`.
*   As três operações devem estar na mesma transação. Se uma falhar, nada deve ser alterado.

### `Reserva atualizarReserva(Reserva reserva)`
*   **Regra de Negócio:** Permite atualizar apenas a `data_sessao` **OU** o `cliente_id`.
*   Lançar `RuntimeException("Registro de reserva não localizado para atualização")` se o ID não existir ou se nenhum campo for informado.

### `void excluirCliente(Integer id)`
*   **Regra de Negócio:** Um cliente só pode ser excluído se não tiver reservas futuras (onde `data_sessao` é maior ou igual à data atual).
*   Lançar `RuntimeException("Cliente possui reservas futuras e não pode ser excluído")`.

### `List<RelatorioCancelamento> gerarRelatorioCancelamentosCriticos()`
*   **Regra de Negócio:** Retornar apenas cancelamentos onde a `taxa_multa` seja superior a R$ 50,00.

### `List<Filme> buscarFilmesPorIds(List<Integer> ids)`
*   **Regra de Negócio:** Validar se a lista é nula ou vazia.

### `List<Cliente> buscarClientesPorNome(String nome)`
*   **Regra de Negócio:** Validar nulo, vazio e mínimo de 3 caracteres (Short-circuit!).

---

## 3. Critérios de Avaliação Especial
*   **Transações:** Uso correto de `setAutoCommit(false)`, `commit()` e `rollback()`.
*   **Arquitetura:** Conexão sendo aberta no Service e passada para os Repositories em operações transacionais.
*   **SQL:** Uso correto de Joins para o relatório.

Boa sorte, mestre! O cinema depende de você.
