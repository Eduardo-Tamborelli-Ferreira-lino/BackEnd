package org.example.repository.Leitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.connection.ConnectionFactory;
import org.example.model.Leitor;

public class LeitorRepositoryImpl implements LeitorRepository{

    @Override
    public void excluirLeitor(Integer id) throws SQLException {

        String command = """
                DELETE FROM Leitor
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Leitor> buscarLeitorPorNome(String nome) throws SQLException {

        List<Leitor> leitor = new ArrayList<>();

        String command = """
                SELECT 
                id,
                nome,
                email,
                ativo
                FROM Leitor
                WHERE nome LIKE ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                leitor.add(new Leitor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getBoolean("ativo")
                ));
            }
        }
        return leitor;
    }

}
