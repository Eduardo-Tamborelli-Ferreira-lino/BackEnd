package org.example.DAO;

import org.example.Conexao.Conexao;
import org.example.Model.OrdemManutencao;
import org.example.Model.OrdemPeca;
import org.example.Model.Pecas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PecasDao {

    public boolean validarEntrada (Pecas pecas) throws SQLException{
        String command = """
                SELECT
                nome
                FROM Peca
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getString("nome").equals(pecas.getNome())){
                    return true;
                }
            }
        }
        return false;
    }

    public void cadastrarPecas (Pecas pecas) throws SQLException{
        String command = """
                INSERT INTO Peca(
                nome,
                estoque)
                VALUES
                (?, ?)
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, pecas.getNome());
            stmt.setDouble(2, pecas.getEstoque());
            stmt.executeUpdate();
        }
    }

    public ArrayList<Pecas> listarPecas () throws SQLException{
        ArrayList<Pecas> pecas = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                estoque
                FROM Peca
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                pecas.add(new Pecas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("estoque")
                ));
            }
            return pecas;
        }
    }

    public Pecas buscarPecas (int idPeca) throws SQLException{
        Pecas peca = null;
        String command = """
                SELECT
                id,
                nome,
                estoque
                FROM Peca
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, idPeca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                peca = new Pecas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("estoque")
                );
            }
        }
        return peca;
    }

    public void atualizarEstoque (Pecas pecas, OrdemPeca ordem) throws SQLException{
        String command = """
                UPDATE Peca
                SET estoque = estoque - ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setDouble(1,ordem.getQuantidade());
            stmt.setInt(2, pecas.getId());
            stmt.executeUpdate();
        }
    }
}
