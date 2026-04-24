package org.example.DAO;

import org.example.Conexao.Conexao;
import org.example.Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaquinasDao {

    public boolean validarEntrada(Maquinas maquinas) throws SQLException {
        String command = """
                SELECT
                nome,
                setor
                FROM Maquina
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getString("nome").equals(maquinas.getCadastro())
                        && rs.getString("setor").equals(maquinas.getSetor())){
                    return true;
                }
            }
            return false;
        }
    }

    public void cadastrarMaquinas (Maquinas maquinas) throws SQLException{
        String command = """
                INSERT INTO Maquina (
                nome,
                setor,
                status)
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){

            stmt.setString(1, maquinas.getCadastro());
            stmt.setString(2, maquinas.getSetor());
            stmt.setString(3, maquinas.getStatus());
            stmt.executeUpdate();

        }
    }

    public ArrayList<Maquinas> listarMaquinas () throws SQLException {
        ArrayList <Maquinas> maquinas = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                setor,
                status
                FROM Maquina
                WHERE status = 'Operacional'
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                maquinas.add(new Maquinas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("setor"),
                        rs.getString("status")
                ));
            }
            return maquinas;
        }
    }

    public void criandoManutencao (int opcao) throws SQLException{
        String command = """
                UPDATE Maquina
                SET status = 'Em Manutenção'
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt (1, opcao);
            stmt.executeUpdate();
        }
    }

    public void atualizarStatus (OrdemManutencao ordem) throws SQLException{
        String command = """
                UPDATE Maquina
                SET status = 'Operacional'
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt (1, ordem.getIdMaquina());
            stmt.executeUpdate();
        }
    }
}
