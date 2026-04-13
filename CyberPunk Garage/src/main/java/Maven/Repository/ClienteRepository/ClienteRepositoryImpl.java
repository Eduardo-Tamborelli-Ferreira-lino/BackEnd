package Maven.Repository.ClienteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Maven.Model.Cliente;
import Maven.Util.ConnectionFactory;

public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public Cliente save(Cliente cliente) throws SQLException {

        String command = """
                INSERT INTO clientes(
                nome,
                email)
                VALUES
                (?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                cliente.setId(rs.getInt(1));

                return cliente;
            } else {

                throw new SQLException("Erro ao cadastrar cliente. ");

            }
        }
    }

    @Override
    public Cliente updateCliente(Cliente cliente) throws SQLException {

        String command = """
                UPDATE clientes
                SET
                nome = ?,
                email = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setInt(3, cliente.getId());

            stmt.executeUpdate();

            return cliente;
        }
    }

    @Override
    public ArrayList<Cliente> findAll() throws SQLException {

        ArrayList<Cliente> clientes = new ArrayList<>();

        String command = """
                SELECT
                id,
                nome,
                email
                FROM clientes
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                var cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"));

                clientes.add(cliente);

            }

            return clientes;
        }
    }

    @Override
    public Cliente findById(int chosenId) throws SQLException {

        Cliente cliente;

        String command = """
                SELECT
                id,
                nome,
                email
                FROM clientes
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                var findCliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"));

                cliente = findCliente;

                return cliente;

            } else {

                throw new SQLException("Cliente não encontrado. ");

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

            } else {

                return false;

            }
        }
    }

}
