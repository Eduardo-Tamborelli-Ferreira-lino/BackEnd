package org.example.DAO;

import org.example.Connection.Conexao;
import org.example.DTO.ListarLivrosDTO;
import org.example.Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDAO {
    static final AutorDAO AUTOR_DAO = new AutorDAO();

    public void cadastrarLivro(Livro livro) throws SQLException {
        if (AUTOR_DAO.autorExiste(livro.getAutorId())) {
            String command = """
                    INSERT INTO Livro (
                    autor_id,
                    titulo,
                    ano_publicacao
                    )
                    VALUES
                    (?, ?, ?)
                    """;
            try (Connection conn = Conexao.conectar();
                    PreparedStatement stmt = conn.prepareStatement(command)) {
                stmt.setInt(1, livro.getAutorId());
                stmt.setString(2, livro.getTitulo());
                if (livro.getAnoPublicacao() == null) {
                    stmt.setNull(3, java.sql.Types.INTEGER);
                } else {
                    stmt.setInt(3, livro.getAnoPublicacao());
                }
                stmt.executeUpdate();
            }
        } else {
            throw new RuntimeException("Autor Não Encontrado Verifique Que o ID Foi Inserido De forma Correta. ");
        }
    }

    public boolean verificarCadastro(Livro livro) throws SQLException {
        String command = """
                SELECT
                id,
                autor_id,
                titulo,
                ano_publicacao
                FROM Autor
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("titulo").equals(livro.getTitulo())) {
                    return false;
                }
            }
            return true;
        }
    }

    public void atualizarLivroTitulo(String nome, int idEscolhido) throws SQLException {
        if (!livroExiste(idEscolhido)) {
            throw new RuntimeException("Livro Não Encontrado Verifique Que o ID Foi Inserido De forma Correta. ");
        }
        String command = """
                UPDATE Livro
                SET titulo = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public void atualizarLivroAnoPublicacao(Integer ano, int idEscolhido) throws SQLException {
        if (!livroExiste(idEscolhido)) {
            throw new RuntimeException("Livro Não Encontrado Verifique Que o ID Foi Inserido De forma Correta. ");
        }
        String command = """
                UPDATE Livro
                SET ano_publicacao = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, ano);
            stmt.setInt(2, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public void atualizarLivroAutor(int idAutor, int idEscolhido) throws SQLException {
        if (!livroExiste(idEscolhido)) {
            throw new RuntimeException("Livro Não Encontrado Verifique Que o ID Foi Inserido De forma Correta. ");
        }
        String command = """
                UPDATE Livro
                SET autor_id = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idAutor);
            stmt.setInt(2, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public ArrayList<ListarLivrosDTO> listarLivros() throws SQLException {
        ArrayList<ListarLivrosDTO> livros = new ArrayList<>();
        String command = """
                SELECT
                a.nome,
                l.id,
                l.titulo,
                l.ano_publicacao,
                l.autor_id
                FROM Livro l
                JOIN Autor a
                ON l.autor_id = a.id
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                livros.add(new ListarLivrosDTO(
                        rs.getInt("l.autor_id"),
                        rs.getString("a.nome"),
                        rs.getInt("l.id"),
                        rs.getString("l.titulo"),
                        rs.getInt("l.ano_publicacao")));
            }
        }
        return livros;
    }

    public void deletarLivro(int idEscolhido) throws SQLException {
        if (!livroExiste(idEscolhido)) {
            throw new RuntimeException("Livro Não Encontrado Verifique Que o ID Foi Inserido De forma Correta. ");
        }
        String command = """
                DELETE
                FROM Livro
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public boolean livroExiste(int idEscolhido) throws SQLException {
        String command = """
                SELECT
                a.nome,
                l.id,
                l.titulo,
                l.ano_publicacao
                l.autor_id
                FROM Livro l
                JOIN Autor a
                ON l.autor_id = a.id
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    public Livro buscarLivroId(int idEscolhido) throws SQLException {
        String command = """
                SELECT
                id,
                autor_id,
                titulo,
                ano_publicacao
                FROM Livro
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idEscolhido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                        rs.getInt("id"),
                        rs.getInt("autor_id"),
                        rs.getString("titulo"),
                        rs.getInt("ano_publicacao"));
            } else {
                throw new RuntimeException("Autor Não Encontrado Verifique Que o ID Foi Inserido De forma Correta. ");
            }
        }
    }
}
