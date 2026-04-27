package senai.repository.fornecedor;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import senai.database.Conexao;
import senai.model.Fornecedor;

public class FornecedorRepositoryImpl implements FornecedorRepository {

    @Override
    public Fornecedor save(Fornecedor fornecedor) throws SQLException {

        String command = """
                INSERT INTO Fornecedor (
                nome,
                cnpj)
                VALUES
                (?, ?)
                """;
        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                fornecedor.setId(rs.getInt(1));
            }

            return fornecedor;
        }
    }

    @Override
    public Fornecedor findById(int idGerado) throws SQLException {

        Fornecedor fornecedor;

        String command = """
                SELECT
                id,
                nome,
                cnpj
                FROM Fornecedor
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idGerado);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fornecedor = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"));
                return fornecedor;
            } else {
                throw new RuntimeException("Id do Fornecedor não encontrado!");
            }
        }
    }

    @Override
    public ArrayList<Fornecedor> findAll() throws SQLException {

        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        String command = """
                SELECT
                id,
                nome,
                cnpj
                FROM Fornecedor
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                fornecedores.add(new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj")));
            }
            return fornecedores;
        }
    }

    @Override
    public void update(Fornecedor atualizado) throws SQLException {

        String command = """
                UPDATE Fornecedor
                SET
                nome = ?,
                cnpj = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, atualizado.getNome());
            stmt.setString(2, atualizado.getCnpj());
            stmt.setInt(3, atualizado.getId());

            int linhasAlterada = stmt.executeUpdate();

            if (linhasAlterada <= 0) {
                throw new RuntimeException("Id do fornecedor não encontrado!");
            }
        }
    }

    @Override
    public void delete(int idGerado) throws SQLException {

        String command = """
                DELETE FROM Fornecedor
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, idGerado);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Id do Fornecedor não encontrado!");
            }
        }
    }

}
