package org.example.repository.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.example.connection.ConnectionFactory;
import org.example.model.Reserva;

public class ReservaRepositoryImpl implements ReservaRepository{

    @Override
    public Reserva buscarReservaClienteFilme(Integer id) throws SQLException {

        Reserva reserva;

        String command = """
                SELECT
                id,
                cliente_id,
                filme_id,
                data_reserva,
                data_sessao
                FROM Reserva
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reserva = new Reserva(
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("filme_id"),
                    rs.getObject("data_reserva", LocalDate.class),
                    rs.getObject("data_sessao", LocalDate.class)
                );
                return reserva;
            }
        }
        throw new RuntimeException("Reserva não encontrada ou inválida");
    }

    @Override
    public Reserva atualizarReservaCliente(Reserva reserva) throws SQLException {

        String command = """
                UPDATE Reserva
                SET cliente_id = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, reserva.getCliente_id());
            stmt.setInt(2, reserva.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0 ) {
                throw new RuntimeException("Registro de reserva não localizado para atualização");
            }
        }
        return reserva;
    }

    @Override
    public Reserva atualizarReservaDataSessao(Reserva reserva) throws SQLException {

        String command = """
                UPDATE Reserva
                SET data_sessao = ?
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setObject(1, reserva.getData_sessao());
            stmt.setInt(2, reserva.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0 ) {
                throw new RuntimeException("Registro de reserva não localizado para atualização");
            }
        }
        return reserva;
    }

    public Reserva buscarReservaId(Integer id) throws SQLException {

        Reserva reserva;

        String command = """
                SELECT
                id,
                cliente_id,
                filme_id,
                data_reserva,
                data_sessao
                FROM Reserva
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reserva = new Reserva(
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("filme_id"),
                    rs.getObject("data_reserva", LocalDate.class),
                    rs.getObject("data_sessao", LocalDate.class)
                );
                return reserva;
            }
        }
        throw new RuntimeException("Registro de reserva não localizado para atualização");
    }

    @Override
    public Boolean buscarClienteReserva(Integer id) throws SQLException {

        String command = """
                SELECT
                id,
                cliente_id,
                filme_id,
                data_reserva,
                data_sessao
                FROM Reserva
                WHERE cliente_id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return false;
            }

            return true;
        }
    }

}