package org.example.repository.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.connection.ConnectionFactory;
import org.example.model.RelatorioIncidente;

public class RelatorioRepositoryImpl implements RelatorioRepository{

    @Override
    public ArrayList<RelatorioIncidente> relatorioIncidentes() throws SQLException {

        ArrayList<RelatorioIncidente> relatorioIncidentes = new ArrayList<>();

        String command = """
            SELECT
            m.nome,
            m.cnh,
            v.placa,
            i.descricao,
            i.gravidade
            FROM Incidente i
            JOIN Alocacao a
            ON a.id = i.alocacao_id
            JOIN Motorista m
            ON m.id = a.motorista_id
            JOIN Veiculo v
            ON v.id = a.veiculo_id
            """;
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                relatorioIncidentes.add(new RelatorioIncidente(
                    rs.getString("m.nome"),
                    rs.getString("m.cnh"),
                    rs.getString("v.placa"),
                    rs.getString("i.descricao"),
                    rs.getString("i.gravidade")
                ));
            }
        }
        return relatorioIncidentes;
    }
}
