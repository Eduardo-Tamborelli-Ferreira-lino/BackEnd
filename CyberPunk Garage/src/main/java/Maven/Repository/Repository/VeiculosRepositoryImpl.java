package Maven.Repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Maven.Model.Veiculos;
import Maven.Repository.Interface.VeiculosRepository;
import Maven.Util.ConnectionFactory;

public class VeiculosRepositoryImpl implements VeiculosRepository{

    @Override
    public Veiculos save(Veiculos veiculos) throws SQLException {
        String command = """
                INSERT INTO veiculos(
                modelo,
                placa,
                cliente_id)
                VALUES
                (?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement
        (command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, veiculos.getModelo());
            stmt.setString(2, veiculos.getPlaca());
            stmt.setInt(3, veiculos.getCliente());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                
                veiculos.setId(rs.getInt(1));

                return veiculos;
            }
            else {
                
                throw new SQLException("Erro ao cadastrar cliente. ");

            }
        }
    }

    @Override
    public Veiculos updateVeiculos(Veiculos veiculos) throws SQLException {
        String command = """
                UPDATE veiculos
                SET 
                modelo = ?,
                placa = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, veiculos.getModelo());
            stmt.setString(2, veiculos.getPlaca());

            stmt.executeUpdate();

            return veiculos;
        }
    }

    @Override
    public ArrayList<Veiculos> findAll() throws SQLException {
         
        ArrayList<Veiculos>  veiculos = new ArrayList<>();;

        String command = """
                SELECT
                id,
                modelo,
                placa,
                cliente_id
                FROM veiculos
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                var veiculo = new Veiculos(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getString("placa"),
                    rs.getInt("cliente_id")
                );

                veiculos.add(veiculo);
                
            }
                
            return veiculos;
        }
    }

    @Override
    public Veiculos findById(int chosenId) throws SQLException {

        Veiculos veiculo;

        String command = """
                SELECT
                id,
                modelo,
                placa,
                cliente_id
                FROM veiculos
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                var findVeiculo = new Veiculos(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getString("placa"),
                    rs.getInt("cliente_id")
                );

                veiculo = findVeiculo;

                return veiculo;

            }
            else {

                throw new SQLException("Cliente não encontrado. ");

            }
        }
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {

        String command = """
                DELETE FROM veiculos
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
