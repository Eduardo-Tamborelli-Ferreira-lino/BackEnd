package org.example.repository.Alocacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.example.connection.ConnectionFactory;
import org.example.model.Alocacao;
import org.example.model.Incidente;

public class AlocacaoRepositoryImpl implements AlocacaoRepository {

    @Override
    public Integer buscarIdVeiculo(Incidente incidente) throws SQLException {

        String command = """
                SELECT
                veiculo_id
                FROM Alocacao
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, incidente.getAlocacao_id());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Integer veiculo_id = rs.getInt("veiculo_id");
                return veiculo_id;
            }
            throw new RuntimeException("Alocação não encontrada ou inválida");
        }
    }

    @Override
    public Alocacao buscarPorId(Integer id) throws SQLException {

        Alocacao alocacao;

        String command = """
                SELECT
                id,
                motorista_id,
                veiculo_id,
                data_inicio,
                data_fim
                FROM Alocacao
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return alocacao = new Alocacao(
                        rs.getInt("id"),
                        rs.getInt("motorista_id"),
                        rs.getInt("veiculo_id"),
                        rs.getObject("data_inicio", LocalDate.class),
                        rs.getObject("data_fim", LocalDate.class));
            }
            throw new RuntimeException("Registro de alocação não localizado para atualizaçã");
        }
    }

    @Override
    public Boolean buscarAlocacaoMotorista(Integer id) throws SQLException {

        String command = """
                SELECT
                id,
                motorista_id,
                veiculo_id,
                data_inicio,
                data_fim
                FROM Alocacao
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
            return false;
        }
    }

    @Override
    public Alocacao atualizarMotorista(Alocacao alocacao) throws SQLException {

        String command = """
                UPDATE Alocacao
                SET motorista_id = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, alocacao.getMotorista_id());
            stmt.setInt(2, alocacao.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Registro de alocação não localizado para atualização");
            }
            return alocacao;
        }
    }

    @Override
    public Alocacao atualizarDataFim(Alocacao alocacao) throws SQLException {

        String command = """
                UPDATE Alocacao
                SET data_fim = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setObject(1, alocacao.getData_fim());
            stmt.setInt(2, alocacao.getId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Registro de alocação não localizado para atualização");
            }
            return alocacao;
        }
    }
}
