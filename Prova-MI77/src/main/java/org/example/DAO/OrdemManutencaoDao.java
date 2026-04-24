package org.example.DAO;

import org.example.Conexao.Conexao;
import org.example.Model.OrdemManutencao;
import org.example.Model.OrdemPeca;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdemManutencaoDao {

    public void cadastrarOrdem (OrdemManutencao manutencao) throws SQLException{
        String command = """
                INSERT INTO OrdemManutencao(
                idMaquina,
                idTecnico,
                dataSolicitacao,
                status)
                VALUES
                (?, ?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, manutencao.getIdMaquina());
            stmt.setInt(2, manutencao.getIdTecnico());
            stmt.setDate(3, Date.valueOf(manutencao.getDataManutencao()));
            stmt.setString(4, manutencao.getStatus());
            stmt.executeUpdate();
        }
    }

    public ArrayList<OrdemManutencao> listarOrdens () throws SQLException{
        ArrayList<OrdemManutencao> ordens = new ArrayList<>();
        String command = """
                SELECT
                id,
                dataSolicitacao,
                status
                FROM OrdemManutencao
                WHERE status = 'Pendente'
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ordens.add(new OrdemManutencao(
                        rs.getInt("id"),
                        rs.getObject("dataSolicitacao", LocalDate.class),
                        rs.getString("status")
                ));
            }
            return ordens;
        }
    }

    public void atualizarOrdem (OrdemPeca ordem) throws SQLException{
        String command = """
                UPDATE OrdemManutencao
                SET status = 'Executada'
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, ordem.getIdOrdem());
            stmt.executeUpdate();
        }
    }

    public OrdemManutencao buscarOrdem (OrdemPeca ordem) throws SQLException{
        OrdemManutencao ordemManutencao = null;
        String command = """
                SELECT
                id,
                idMaquina,
                status
                FROM OrdemManutencao
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, ordem.getIdOrdem());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                ordemManutencao = new OrdemManutencao(
                        rs.getInt("id"),
                        rs.getInt("idMaquina"),
                        rs.getString("status")
                );
            }
        }
        return ordemManutencao;
    }
}
