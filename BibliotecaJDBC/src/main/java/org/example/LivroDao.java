package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LivroDao {

    public void cadastrarLivro (String titulo, int qtd)throws SQLException {
        String command = """
                INSERT INTO livro (
                titulo,
                quantidade)
                VALUES
                (?,?)
                """;
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setString(1, titulo);
            stmt.setInt(2,qtd);
            stmt.executeUpdate();
            conn.commit();
        }catch (SQLException e) {
            // 3. Se deu erro em QUALQUER um dos passos, desfaz tudo
            if (conn != null) {
                conn.rollback();
                System.err.println("Erro! O banco voltou ao estado anterior.");
            }
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void realizarEmprestimo (int id, String nome)throws SQLException{
        String commandEmprestimo = """
                INSERT INTO emprestimo (
                livro_id,
                nome_cliente)
                VALUES
                (?,?)
                """;
        String commandLivro = """
                UPDATE livro
                SET quantidade = quantidade - 1
                WHERE id = ?
                """;
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmtEmprestimo = conn.prepareStatement(commandEmprestimo);
            stmtEmprestimo.setInt(1,id);
            stmtEmprestimo.setString(2,nome);
            stmtEmprestimo.executeUpdate();
            PreparedStatement stmtLivro = conn.prepareStatement(commandLivro);
            stmtLivro.setInt(1,id);
            stmtLivro.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            if ( conn != null){
                conn.rollback();
                System.out.println("Erro! O banco voltou ao estado anterior.");
            }
            throw e;
        }finally {
            if (conn != null) conn.close();
        }
    }

    public ArrayList<Livro> listarLivros() throws SQLException{
        ArrayList<Livro> livros = new ArrayList<>();
        String command = """
                SELECT id,
                titulo,
                quantidade
                FROM livro
                """;
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                livros.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("quantidade")
                ));
            }
            conn.commit();
            return livros;
        }catch (SQLException e){
            if (conn != null){
                conn.rollback();
                System.out.println("Erro! O banco voltou ao estado anterior.");
            }
            throw e;
        }finally {
            if (conn != null) conn.close();
        }
    }

    public void excluirLivro(int id)throws SQLException{
        String command = """
                DELETE
                FROM livro
                WHERE id = ?
                """;
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            if (conn != null){
                conn.rollback();
                System.out.println("Erro! O banco voltou ao estado anterior.");
            }
            throw e;
        }finally {
            if (conn != null) conn.close();
        }
    }

    public void atualizarLivroTitulo(String titulo, int id)throws SQLException{
        String command = """
                UPDATE livro
                SET titulo = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setString(1, titulo);
            stmt.setInt(2,id);
            stmt.executeUpdate();
        }
    }

    public void atualizarLivroQuantidade(int quantidade, int id)throws SQLException{
        String command = """
                UPDATE livro
                SET quantidade = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setInt(1, quantidade);
            stmt.setInt(2,id);
            stmt.executeUpdate();
        }
    }
}
