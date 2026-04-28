package org.example.repository.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.connection.ConnectionFactory;

public class VeiculosRepositoryImpl implements VeiculoRepository{

    @Override
    public void alterarStatus(Integer veiculo_id) throws SQLException {

        String command = """
                UPDATE Veiculo
                SET status = 'MANUTENCAO'
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            
            stmt.setInt(1, veiculo_id);

            stmt.executeUpdate();
        }
    }

}
