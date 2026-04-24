package org.example.DAO;

import org.example.Conexao.Conexao;
import org.example.Model.OrdemPeca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdemPecaDao {

    public void cadastrarOrdemPeca (OrdemPeca ordemPeca) throws SQLException{
        String command = """
                INSERT INTO OrdemPeca(
                idOrdem,
                idPeca,
                quantidade)
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, ordemPeca.getIdOrdem());
            stmt.setInt(2, ordemPeca.getIdPeca());
            stmt.setDouble(3, ordemPeca.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public ArrayList<OrdemPeca> listarOrdens () throws SQLException {
        ArrayList <OrdemPeca> ordens = new ArrayList<>();
        String command = """
                SELECT
                op.idOrdem,
                op.idPeca,
                op.quantidade,
                om.status
                FROM OrdemPeca op
                JOIN OrdemManutencao om
                ON op.idOrdem = om.id
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ordens.add(new OrdemPeca(
                        rs.getInt("idOrdem"),
                        rs.getInt("idPeca"),
                        rs.getDouble("quantidade")
                ));
            }
        }
        return ordens;
    }

}
