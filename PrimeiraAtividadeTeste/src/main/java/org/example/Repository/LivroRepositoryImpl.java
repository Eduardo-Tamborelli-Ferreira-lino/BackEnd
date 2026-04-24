package org.example.Repository;

import org.example.Connection.*;
import org.example.Model.*;
import org.example.Service.LivroService;

import java.sql.*;
import java.util.ArrayList;

public class LivroRepositoryImpl implements LivroRepository {

    public Livro inserirLivro (Livro livro) throws SQLException {
        String command = """
                INSERT INTO Livro(
                titulo,
                autor,
                ano,
                disponivel)
                VALUES
                (?, ?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     command, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                livro.setId(rs.getInt(1));
            }
        }
        return livro;
    }

    public ArrayList<Livro> consultarLivros () throws SQLException {
        ArrayList <Livro> livros = new ArrayList<>();
        String command = """
                SELECT
                id,
                titulo,
                autor,
                ano,
                disponivel
                FROM Livro
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                livros.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getBoolean("disponivel")
                ));
            }
        }
        return livros;
    }

    public void atualizarStatus (Boolean disponivel, int livroId) throws SQLException {
        String command = """
                UPDATE Livro
                SET disponivel = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setBoolean(1, disponivel);
            stmt.setInt(2, livroId);
            stmt.executeUpdate();
        }
    }

    public Livro buscarLivros (int livroId) throws SQLException {
        Livro livro = null;
        String command = """
                SELECT
                id,
                titulo,
                autor,
                ano,
                disponivel
                FROM Livro
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, livroId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano"),
                        rs.getBoolean("disponivel")
                );
            }
        }
        return null;
    }
}
