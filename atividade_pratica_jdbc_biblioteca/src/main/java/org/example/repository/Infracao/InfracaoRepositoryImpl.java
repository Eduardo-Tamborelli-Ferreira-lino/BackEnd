package org.example.repository.Infracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.connection.ConnectionFactory;
import org.example.model.Infracao;

public class InfracaoRepositoryImpl implements InfracaoRepository{

    @Override
    public Infracao save(Infracao infracao) throws SQLException {

        String command = """
                INSERT INTO Infracao (
                emprestimo_id,
                descricao,
                gravidade)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, infracao.getEmprestimo_id());
            stmt.setString(2, infracao.getDescricao());
            stmt.setString(3, infracao.getGravidade());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                infracao.setId(rs.getInt(1));
                
                return infracao;
            }

            throw new RuntimeException("Infração não encontrada ou inválida");
        }
    }

}
