package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmprestimoDao {

    public ArrayList<Emprestimo> listarEmprestimos() throws SQLException {
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        String command = """
                SELECT id,
                livro_id,
                data_emprestimo,
                nome_cliente
                FROM emprestimo
                """;
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                emprestimos.add(new Emprestimo(
                        rs.getInt("id"),
                        rs.getInt("livro_id"),
                        rs.getString("data_emprestimo"),
                        rs.getString("nome_cliente")
                ));
            }
            conn.commit();
            return emprestimos;
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

    public void excluirEmprestimo (int id) throws SQLException{
        String command = """
                DELETE
                FROM emprestimo
                WHERE id = ?
                """;
        String commandLivro = """
                UPDATE livro
                SET quantidade = quantidade + 1
                WHERE id = ?
                """;
        Connection conn = null;
        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmtEmprestimo = conn.prepareStatement(command);
            stmtEmprestimo.setInt(1 , id);
            stmtEmprestimo.executeUpdate();
            PreparedStatement stmtLivro = conn.prepareStatement(commandLivro);
            stmtLivro.setInt(1, id);
            stmtLivro.executeUpdate();
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

    public void atualizarEmprestino (String nome , int id) throws SQLException{
        String command = """
                UPDATE emprestimo
                SET nome_cliente = ?
                WHERE id = ?
                """;
        Connection conn = null;
        try{
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setString(1, nome);
            stmt.setInt(2, id);
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
}
