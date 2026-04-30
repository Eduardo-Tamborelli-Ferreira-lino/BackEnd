package org.example.repository.Reserva;

import java.sql.SQLException;

import org.example.model.Reserva;

public interface ReservaRepository {

    Reserva buscarReservaClienteFilme(Integer id) throws SQLException;
    
    Reserva buscarReservaId(Integer id) throws SQLException;

    Reserva atualizarReservaCliente(Reserva reserva) throws SQLException;

    Reserva atualizarReservaDataSessao(Reserva reserva) throws SQLException;

    Boolean buscarClienteReserva(Integer id) throws SQLException;
}
