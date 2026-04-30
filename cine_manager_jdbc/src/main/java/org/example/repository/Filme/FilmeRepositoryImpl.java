package org.example.repository.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.connection.ConnectionFactory;
import org.example.model.Filme;

public class FilmeRepositoryImpl implements FilmeRepository{

    @Override
    public void alterarStatus(Integer id) throws SQLException {

        String command = """
                UPDATE Filme
                SET status = 'MANUTENCAO'
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Reserva não encontrada ou inválida");
            }
        }
    }

    @Override
    public List<Filme> buscarFilmes(List<Integer> ids) throws SQLException {

        List<Filme> filmes = new ArrayList<>();

        String command = """
                SELECT
                id,
                titulo,
                genero,
                status
                FROM Filme
                WHERE id IN (
                """;
        
        for (int i = 0; i < ids.size(); i++) {
            command += "?";

            if (i == ids.size() - 1) {
                break;
            }

            command += ","; 
        }
        command += ")";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            for (int i = 0; i < ids.size(); i++) {
                stmt.setInt(i + 1, ids.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                filmes.add(new Filme(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("genero"),
                    rs.getString("status")
                ));
            }
            return filmes;
        }
    }

}
