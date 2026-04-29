package org.example.service;

import org.example.model.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaServiceImpl implements CinemaService {

    // Instancie seus repositórios aqui

    @Override
    public Cancelamento registrarCancelamento(Cancelamento cancelamento) throws SQLException {
        // TODO: Implementar regra TRANSACIONAL
        // Se motivo == "DANO_AO_EQUIPAMENTO" -> Desativar Cliente, Filme em MANUTENCAO e salvar cancelamento.
        return null;
    }

    @Override
    public Reserva atualizarReserva(Reserva reserva) throws SQLException {
        // TODO: Atualizar data_sessao OU cliente_id
        return null;
    }

    @Override
    public void excluirCliente(Integer id) throws SQLException {
        // TODO: Excluir apenas se não houver reservas futuras
    }

    @Override
    public List<RelatorioCancelamento> gerarRelatorioCancelamentosCriticos() throws SQLException {
        // TODO: Filtrar apenas multas > 50.00
        return new ArrayList<>();
    }

    @Override
    public List<Filme> buscarFilmesPorIds(List<Integer> ids) throws SQLException {
        // TODO: Validação de lista
        return new ArrayList<>();
    }

    @Override
    public List<Cliente> buscarClientesPorNome(String nome) throws SQLException {
        // TODO: Validação Short-circuit (null, empty, < 3 chars)
        return new ArrayList<>();
    }
}
