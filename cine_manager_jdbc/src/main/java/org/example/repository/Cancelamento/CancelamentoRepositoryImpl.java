package org.example.repository.Cancelamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.connection.ConnectionFactory;
import org.example.model.Cancelamento;

public class CancelamentoRepositoryImpl implements CancelamentoRepository{

    @Override
    public Cancelamento registrarCancelamento(Cancelamento cancelamento) throws SQLException {

        String command = """
                INSERT INTO Cancelamento (
                reserva_id,
                motivo,
                taxa_multa)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, cancelamento.getReserva_id());
            stmt.setString(2, cancelamento.getMotivo());
            stmt.setDouble(3, cancelamento.getTaxa_multa());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                cancelamento.setId(rs.getInt(1));
                return cancelamento;
            }
            throw new RuntimeException("Reserva não encontrada ou inválida");
        }
    }

}
