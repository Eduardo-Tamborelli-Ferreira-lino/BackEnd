package org.example.DAO;

import org.example.Connection.Conexao;
import org.example.Model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDAO {

    public boolean verificarEntrada (String cnpj) throws SQLException {
        String command = """
                SELECT
                id,
                nome,
                cnpj
                FROM Fornecedor
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getString("cnpj").equals(cnpj)){
                    return false;
                }
            }
        }
        return true;
    }

    public void cadastrarFornecedor (Fornecedor fornecedor) throws SQLException {
        String command = """
                INSERT INTO Fornecedor (
                nome,
                cnpj )
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.executeUpdate();
        }
    }

    public Fornecedor buscarPorId (int idEscolhido) throws SQLException {
        Fornecedor fornecedor = null;
        String command = """
                SELECT
                id,
                nome,
                cnpj
                FROM Fornecedor
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, idEscolhido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return fornecedor = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj")
                );
            }
        }
        return fornecedor;
    }

    public ArrayList<Fornecedor> listarFornecedor () throws SQLException {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                cnpj
                FROM Fornecedor
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                fornecedores.add(new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj")
                ));
            }
        }
        return fornecedores;
    }

    public void atualizarNomeFornecedor (String novoNome, int idEscolhido) throws SQLException {
        String command = """
                UPDATE Fornecedor
                SET nome = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, novoNome);
            stmt.setInt(2, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public void atualizarCnpjFornecedor (String cnpjNovo, int idEscolhido) throws SQLException {
        String command = """
                UPDATE Fornecedor
                SET cnpj = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, cnpjNovo);
            stmt.setInt(2, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public void atualizarTudoFornecedor (String cnpjNovo, String novoNome, int idEscolhido) throws SQLException {
        String command = """
                UPDATE Fornecedor
                SET
                cnpj = ?,
                nome = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, cnpjNovo);
            stmt.setString(2, novoNome);
            stmt.setInt(3, idEscolhido);
            stmt.executeUpdate();
        }
    }

    public void deletarFornecedor (int idEscolhido) throws SQLException {
        String command = """
                DELETE FROM Fornecedor
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, idEscolhido);
            stmt.executeUpdate();
        }
    }
}
