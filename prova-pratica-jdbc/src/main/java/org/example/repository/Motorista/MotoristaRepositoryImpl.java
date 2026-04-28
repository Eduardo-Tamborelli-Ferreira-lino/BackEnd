package org.example.repository.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.connection.ConnectionFactory;

public class MotoristaRepositoryImpl implements MotoristaRepository{

    @Override
    public void excluirMotorista(Integer id) throws SQLException {

        String command = """
                DELETE FROM Motorista
                WHERE id = ?
                """;
        
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {
            
            stmt.setInt(1, id);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0 ) {
                throw new RuntimeException("Motorista inexistente");
            }
        }
    }

}
