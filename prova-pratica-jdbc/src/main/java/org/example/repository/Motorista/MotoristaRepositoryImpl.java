package org.example.repository.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.connection.ConnectionFactory;
import org.example.model.Motorista;

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

    @Override
    public ArrayList<Motorista> buscarPorNome(String nome) throws SQLException {

        ArrayList<Motorista> motoristas = new ArrayList<>();

        String command = """
                SELECT
                id,
                nome,
                cnh,
                ativo
                FROM Motorista
                WHERE nome LIKE ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                motoristas.add(new Motorista(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cnh"),
                    rs.getBoolean("ativo")
                ));
            }
            return motoristas;
        }
    }

}
