package org.example.repository.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.connection.ConnectionFactory;
import org.example.model.RelatorioInfracao;

public class RelatorioRepositoryImpl implements RelatorioRepository{

    @Override
    public ArrayList<RelatorioInfracao> gerarRelatorio() throws SQLException {

        ArrayList<RelatorioInfracao> relatorio = new ArrayList<>();

        String command = """
                SELECT
                le.nome,
                le.email,
                li.titulo,
                i.descricao,
                i.gravidade
                FROM Infracao i
                JOIN Emprestimo e
                ON e.id = i.emprestimo_id
                JOIN Leitor le
                ON le.id = e.leitor_id
                JOIN Livro li
                ON li.id = e.livro_id
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                if (rs.getString("i.gravidade").equals("MEDIA") || rs.getString("i.gravidade").equals("ALTA")) {
                    relatorio.add(new RelatorioInfracao(
                        rs.getString("le.nome"),
                        rs.getString("le.email"),
                        rs.getString("li.titulo"),
                        rs.getString("i.descricao"),
                        rs.getString("i.gravidade")
                    ));
                }
            }
            return relatorio;
        }
    }

}
