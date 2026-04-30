package org.example.repository.RelatorioCancelamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.connection.ConnectionFactory;
import org.example.model.RelatorioCancelamento;

public class RelatorioRepositoryImpl implements RelatorioRepository{

    @Override
    public List<RelatorioCancelamento> gerarRelatorioCancelamentos() throws SQLException {

        List<RelatorioCancelamento> relatorio = new ArrayList<>();

        String command = """
                SELECT
                cl.nome,
                f.titulo,
                ca.motivo,
                ca.taxa_multa
                FROM Cancelamento ca
                JOIN Reserva r
                ON r.id = ca.reserva_id
                JOIN Filme f
                ON f.id = r.filme_id
                JOIN Cliente cl
                ON cl.id = r.cliente_id
                WHERE ca.taxa_multa > 50
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                relatorio.add(new RelatorioCancelamento(
                    rs.getString("cl.nome"),
                    rs.getString("f.titulo"), 
                    rs.getString("ca.motivo"), 
                    rs.getDouble("ca.taxa_multa")
                ));
            }
        }
        return relatorio;
    }

}
