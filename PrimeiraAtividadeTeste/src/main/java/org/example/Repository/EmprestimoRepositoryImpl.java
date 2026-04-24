package org.example.Repository;

import org.example.Connection.Conexao;
import org.example.Model.Emprestimo;

import java.sql.*;
import java.time.LocalDate;

public class EmprestimoRepositoryImpl {

    public Emprestimo registrarEmprestimo (Emprestimo emprestimo) throws SQLException {
        String command = """
                INSERT INTO Emprestimo(
                livro_id,
                usuario_id,
                data_emprestimo)
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command,
                     Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, emprestimo.getLivroId());
            stmt.setInt(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                emprestimo.setId(rs.getInt(1));
            }
            return emprestimo;
        }
    }

    public void registrarDevolucao (LocalDate dataDevolucao, int emprestimoId ) throws SQLException {
        String command = """
                UPDATE Emprestimo e
                SET data_devolucao = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setDate(1, Date.valueOf(dataDevolucao));
            stmt.setInt(2, emprestimoId);
            stmt.executeUpdate();
        }
    }
}
