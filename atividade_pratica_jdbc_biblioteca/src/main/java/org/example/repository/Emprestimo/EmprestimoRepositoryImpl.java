package org.example.repository.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.example.connection.ConnectionFactory;
import org.example.model.Emprestimo;

public class EmprestimoRepositoryImpl implements EmprestimoRepository{

    @Override
    public Emprestimo buscarLivroId(Integer id) throws SQLException {

        if (id == null) {
            throw new RuntimeException("Infração não encontrada ou inválida");
        }

        Emprestimo emprestimo;

        String command = """
                SELECT
                id,
                leitor_id,
                livro_id,
                data_emprestimo,
                data_devolucao
                FROM Emprestimo
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {
            
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emprestimo = new Emprestimo(
                    rs.getInt("id"),
                    rs.getInt("leitor_id"),
                    rs.getInt("livro_id"),
                    rs.getObject("data_emprestimo", LocalDate.class),
                    rs.getObject("data_devolucao", LocalDate.class)
                );
                return emprestimo;
            }
        }
        throw new RuntimeException("Registro de empréstimo não localizado para atualização");
    }

    @Override
    public Emprestimo atualizarEmprestimoDataDevolucao(Emprestimo emprestimo) throws SQLException {

        String command = """
                UPDATE Emprestimo
                SET data_devolucao = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setObject(1, emprestimo.getData_devolucao());
            stmt.setInt(2, emprestimo.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Registro de empréstimo não localizado para atualização");
            }

            return emprestimo;
        }
    }

    @Override
    public Emprestimo atualizarEmprestimoLeitorID(Emprestimo emprestimo) throws SQLException {

        String command = """
                UPDATE Emprestimo
                SET leitor_id = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, emprestimo.getLeitor_id());
            stmt.setInt(2, emprestimo.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Registro de empréstimo não localizado para atualização");
            }

            return emprestimo;
        }
    }

    @Override
    public Boolean buscarEmprestimoLeitor(Integer id) throws SQLException {

        String command = """
                SELECT
                id,
                leitor_id,
                livro_id,
                data_emprestimo,
                data_devolucao
                FROM Emprestimo
                WHERE leitor_id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return false;
            }
            return true;
        }
    }

}
