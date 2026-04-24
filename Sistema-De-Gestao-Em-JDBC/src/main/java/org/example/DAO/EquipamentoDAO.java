package org.example.DAO;

import org.example.Connection.Conexao;
import org.example.Model.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipamentoDAO {

    public boolean verificarNumero(String numeroSerie) throws SQLException {
        String command = """
                SELECT
                numero_serie
                FROM Equipamento
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("numero_serie").equals(numeroSerie)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void cadastrarEquipamento(Equipamento equipamento) throws SQLException {
        String command = """
                INSERT INTO Equipamento (
                nome,
                numero_serie,
                fornecedor_id)
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getIdFornecedor());
            stmt.executeUpdate();
        }
    }

    public Equipamento buscarPorId(int idEscolhido) throws SQLException {
        Equipamento equipamento = null;
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
            stmt.setInt(1, idEscolhido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return equipamento = new Equipamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("numero_serie"),
                        rs.getInt("fornecedor_id"));
            }
        }
        return equipamento;
    }

    public ArrayList<Equipamento> listarEquipamentoPeloID(int idEscolha) throws SQLException {
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                numero_serie
                FROM Equipamento
                WHERE fornecedor_id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idEscolha);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                equipamentos.add(new Equipamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("numero_serie")));
            }
        }
        return equipamentos;
    }

    public ArrayList<Equipamento> listarEquipamento() throws SQLException {
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                numero_serie,
                fornecedor_id
                FROM Equipamento
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

    public void atualizarNomeEquipamento(String nome, int idEscolhido) throws SQLException {
        String command = """
                UPDATE Equipamento
                SET nome = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public void deletarEquipamento(int idEscolhido) throws SQLException {
        String command = """
                DELETE FROM Equipamento
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idEscolhido);
            stmt.executeUpdate();
        }
    }
}
