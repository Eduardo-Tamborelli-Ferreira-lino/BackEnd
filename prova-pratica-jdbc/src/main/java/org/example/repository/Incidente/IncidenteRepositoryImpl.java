package org.example.repository.Incidente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.connection.ConnectionFactory;
import org.example.model.Incidente;

public class IncidenteRepositoryImpl implements IncidenteRepository{

    @Override
    public Incidente save(Incidente incidente) throws SQLException {

        String command = """
                INSERT INTO Incidente (
                alocacao_id,
                descricao,
                gravidade,
                data_incidente)
                VALUES
                (?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, incidente.getAlocacao_id());
            stmt.setString(2, incidente.getDescricao());
            stmt.setString(3, incidente.getGravidade());
            stmt.setObject(4, incidente.getData_incidente());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                incidente.setId(rs.getInt(1));
                return incidente;
            }

            throw new RuntimeException("Alocação não encontrada ou inválida");
        }
    }

}
