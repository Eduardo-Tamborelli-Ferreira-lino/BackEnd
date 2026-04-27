package senai.repository.equipamento;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import senai.database.Conexao;
import senai.model.Equipamento;

public class EquipamentoRepositoryImpl implements equipamentoRepository {

    @Override
    public Equipamento save(Equipamento equipamento) throws SQLException {

        String command = """
                INSERT INTO Equipamento (
                nome,
                numero_serie,
                fornecedor_id
                )
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getFornecedorId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                equipamento.setId(rs.getInt(1));
                return equipamento;
            }

            else {
                throw new RuntimeException("Fornecedor inválido ou inexistente!");
            }
        }
    }

    @Override
    public Equipamento findById(int chosenId) throws SQLException {

        Equipamento equipamento;

        String command = """
                SELECT
                id,
                nome,
                numero_serie,
                fornecedor_id
                FROM Equipamento
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                equipamento = new Equipamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("numero_serie"),
                        rs.getInt("fornecedor_id"));

                return equipamento;
            }

            else {
                throw new RuntimeException("Id do Equipamento não encontrado!");
            }
        }
    }

    @Override
    public ArrayList<Equipamento> findAll() throws SQLException {

        ArrayList<Equipamento> equipamentos = new ArrayList<>();

        String command = """
                SELECT
                id,
                nome,
                numero_serie,
                fornecedor_id
                FROM Equipamento e
                JOIN LEFT Fornecedor f
                on e.fornecedor = f.id
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                equipamentos.add(new Equipamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("numero_serie"),
                        rs.getInt("fornecedor_id")));
            }
        }
        return equipamentos;
    }

    @Override
    public void update(int chosenId, int fornecedorId) throws SQLException {

        String command = """
                UPDATE Equipamento
                SET fornecedor_id = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, fornecedorId);
            stmt.setInt(2, chosenId);

            stmt.executeUpdate();
        }
        throw new RuntimeException("Equipamento não encontrado para atualização!");
    }

    @Override
    public void delete(int chosenId) throws SQLException {

        String command = """
                DELETE FROM
                Equipamentos
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, chosenId);

            stmt.executeUpdate();

        }
        throw new RuntimeException("Equipamento não encontrado para exclusão!");
    }

}
