package org.example.repository.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.connection.ConnectionFactory;
import org.example.model.Cliente;

public class ClienteRepositoryImpl implements ClienteRepository{

    @Override
    public void alterarAtivo(Integer id) throws SQLException {

        String command = """
                UPDATE Cliente
                SET ativo = false
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {
            
            stmt.setInt(1, id);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Reserva não encontrada ou inválida");
            }
        }
    }

    @Override
    public void excluirCliente(Integer id) throws SQLException {

        String command = """
                DELETE FROM Cliente
                WHERE id = ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas <= 0) {
                throw new RuntimeException("Cliente inexistente");
            }
        }
    }

    @Override
    public List<Cliente> buscarClientesPorNome(String nome) throws SQLException {

        List<Cliente> clientes = new ArrayList<>();

        String command = """
                SELECT
                id,
                nome,
                email,
                ativo
                FROM Cliente
                WHERE nome LIKE ?
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getBoolean("ativo")
                ));
            }
            return clientes;
        }
    }

}
