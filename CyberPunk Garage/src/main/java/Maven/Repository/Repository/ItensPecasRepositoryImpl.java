package Maven.Repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Maven.Model.ItensPecas;
import Maven.Repository.Interface.ItensPecasRepository;
import Maven.Util.ConnectionFactory;

public class ItensPecasRepositoryImpl implements ItensPecasRepository {

    @Override
    public ItensPecas save(ItensPecas itensPecas) throws SQLException {

        String command = """
                INSERT INTO itensPecas(
                nome_peca,
                preco_unitario,
                quantidade,
                os_id
                )
                VALUES
                (?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, itensPecas.getNome_peca());
            stmt.setDouble(2, itensPecas.getPreco_unitario());
            stmt.setInt(3, itensPecas.getQuantidade());
            stmt.setInt(4, itensPecas.getOrdemServico());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                itensPecas.setId(rs.getInt(1));

                return itensPecas;
            } else {

                throw new SQLException("Erro ao cadastrar peças. ");

            }
        }
    }

    @Override
    public ItensPecas updateItensPecas(ItensPecas itensPecas) throws SQLException {

        String command = """
                UPDATE itens_pecas
                SET
                nome_peca = ?,
                preco_unitario = ?,
                quantidade = ?,
                os_id = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, itensPecas.getNome_peca());
            stmt.setDouble(2, itensPecas.getPreco_unitario());
            stmt.setInt(3, itensPecas.getQuantidade());
            stmt.setInt(4, itensPecas.getOrdemServico());
            stmt.setInt(5, itensPecas.getId());

            stmt.executeUpdate();

            return itensPecas;
        }
    }

    @Override
    public ArrayList<ItensPecas> findAll() throws SQLException {

        ArrayList<ItensPecas> itensPecas = new ArrayList<>();

        String command = """
                SELECT
                id,
                nome_peca,
                preco_unitario,
                quantidade,
                os_id
                FROM itens_pecas
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                var Pecas = new ItensPecas(
                        rs.getInt("id"),
                        rs.getString("nome_peca"),
                        rs.getDouble("preco_unitario"),
                        rs.getInt("quantidade"),
                        rs.getInt("os_id"));

                itensPecas.add(Pecas);

            }

            return itensPecas;
        }
    }

    @Override
    public ItensPecas findById(int chosenId) throws SQLException {

        ItensPecas itensPecas;

        String command = """
                SELECT
                id,
                nome_peca,
                preco_unitario,
                quantidade,
                os_id
                FROM itens_pecas
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                var findPecas = new ItensPecas(
                        rs.getInt("id"),
                        rs.getString("nome_peca"),
                        rs.getDouble("preco_unitario"),
                        rs.getInt("quantidade"),
                        rs.getInt("os_id"));

                itensPecas = findPecas;

                return itensPecas;

            } else {

                throw new SQLException("Peça não encontrada. ");

            }
        }
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {

        String command = """
                DELETE FROM clientes
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, chosenId);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas != 0) {
                
                return true;

            }
            else {

                return false;

            }
        }
    }

}
