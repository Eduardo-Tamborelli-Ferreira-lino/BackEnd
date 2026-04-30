package org.example.repository.Cancelamento;

import java.sql.SQLException;

import org.example.model.Cancelamento;

public interface CancelamentoRepository {

    Cancelamento registrarCancelamento(Cancelamento cancelamento) throws SQLException;
}
