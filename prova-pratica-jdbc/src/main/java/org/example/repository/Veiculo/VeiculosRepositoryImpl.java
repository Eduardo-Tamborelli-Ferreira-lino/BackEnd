package org.example.repository.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.connection.ConnectionFactory;
import org.example.model.Veiculo;

public class VeiculosRepositoryImpl implements VeiculoRepository{

    @Override
    public void alterarStatus(Integer veiculo_id) throws SQLException {

        String command = """
                UPDATE Veiculo
                SET status = 'MANUTENCAO'
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)){
            
            stmt.setInt(1, veiculo_id);

            stmt.executeUpdate();
        }
    }

    @Override
    public ArrayList<Veiculo> buscarVeiculosIn(List<Integer> ids) throws SQLException {

        ArrayList<Veiculo> veiculos = new ArrayList<>();

        String command = """
                SELECT
                id,
                placa,
                modelo,
                status
                FROM Veiculo
                WHERE id IN (
                """;

        for (int i = 0; i < ids.size(); i++) {
            command += "?";
            if (i == ids.size() - 1) {
                break;
            }
            command += ",";
        }
        command += ")";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            for (int i = 0; i < ids.size(); i++) {
                stmt.setInt( i + 1, ids.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                veiculos.add(new Veiculo(
                    rs.getInt("id"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("status")
                ));
            }
            return veiculos;
        }
    }

}
