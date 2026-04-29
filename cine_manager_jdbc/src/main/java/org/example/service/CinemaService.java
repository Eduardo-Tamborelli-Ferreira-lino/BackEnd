package org.example.service;

import org.example.model.*;
import java.sql.SQLException;
import java.util.List;

public interface CinemaService {
    Cancelamento registrarCancelamento(Cancelamento cancelamento) throws SQLException;
    Reserva atualizarReserva(Reserva reserva) throws SQLException;
    void excluirCliente(Integer id) throws SQLException;
    List<RelatorioCancelamento> gerarRelatorioCancelamentosCriticos() throws SQLException;
    List<Filme> buscarFilmesPorIds(List<Integer> ids) throws SQLException;
    List<Cliente> buscarClientesPorNome(String nome) throws SQLException;
}
