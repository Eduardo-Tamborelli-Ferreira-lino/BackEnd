package org.example.repository.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.connection.ConnectionFactory;
import org.example.model.Livro;

public class LivroRepositoryImpl implements LivroRepository{

    @Override
    public void alterarStatus(Integer id) throws SQLException {

        String command = """
                UPDATE Livro
                SET status = 'MANUTENCAO'
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Infração não encontrada ou inválida");
            }
        }
    }

    @Override
    public ArrayList<Livro> buscarLivros(List<Integer> id) throws SQLException {

        ArrayList<Livro> livros = new ArrayList<>();

        String command = """
                SELECT
                id,
                titulo,
                isbn,
                status
                FROM Livro
                WHERE id IN (
                """;
        
        for (int i = 0; i < id.size(); i++) {

            command += "?";

            if (i == id.size() - 1) {
                break;
            }

            command += ",";
        }

        command += ")";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            for (int i = 0; i < id.size(); i++) {
                
                stmt.setInt(i + 1, id.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                livros.add(new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("isbn"),
                    rs.getString("status")
                ));
            }
            return livros;
        }
    }

}
