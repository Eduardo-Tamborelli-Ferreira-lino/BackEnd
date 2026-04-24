package org.example.DAO;

import org.example.Connection.Conexao;
import org.example.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutorDAO {

    public void cadastrarAutor(Autor autor) throws SQLException {
        String command = """
                INSERT INTO Autor (
                nome,
                nacionalidade
                )
                VALUES
                (?, ?)
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.executeUpdate();
        }
    }

    public boolean verificarCadastro(Autor autor) throws SQLException {
        String command = """
                SELECT
                id,
                nome,
                nacionalidade
                FROM Autor
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("nome").equals(autor.getNome()) &&
                        rs.getString("nacionalidade").equals(autor.getNacionalidade())) {
                    return false;
                }
            }
            return true;
        }
    }

    public Autor buscarPorId(int idEscolhido) throws SQLException {
        String command = """
                SELECT
                id,
                nome,
                nacionalidade
                FROM Autor
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idEscolhido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Autor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("nacionalidade"));
            } else {
                throw new RuntimeException("Autor Não Encontrado Verifique Que o ID Foi Inserido De forma Correta. ");
            }
        }
    }

    public ArrayList<Autor> listarAutores() throws SQLException {
        ArrayList<Autor> autores = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                nacionalidade
                FROM Autor
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                autores.add(new Autor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("nacionalidade")));
            }
        }
        return autores;
    }

    public void deletarAutor(int idEscolhido) throws SQLException {
        String command = """
                DELETE
                FROM Autor
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public boolean autorExiste(int autorId) throws SQLException {
        String command = """
                SELECT
                id,
                nome,
                nacionalidade
                FROM Autor
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, autorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }
}
