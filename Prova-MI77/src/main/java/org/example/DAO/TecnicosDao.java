package org.example.DAO;

import org.example.Conexao.Conexao;
import org.example.Model.Tecnicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TecnicosDao {

    public boolean validarEntrada(Tecnicos tecnico) throws SQLException {
        String command = """
                SELECT
                nome,
                especialidade
                FROM Tecnico
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getString("nome").equals(tecnico.getNome()) &&
                rs.getString("especialidade").equals(tecnico.getEspecialidade())){
                    return true;
                }
            }
        }
        return false;
    }

    public void cadastrarTecnico (Tecnicos tecnico) throws SQLException{
        String command = """
            INSERT INTO Tecnico(
            nome,
            especialidade)
            VALUES
            (?, ?)
            """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, tecnico.getNome());
            stmt.setString(2, tecnico.getEspecialidade());
            stmt.executeUpdate();
        }
    }

    public ArrayList<Tecnicos> listarTecnicos () throws SQLException{
        ArrayList<Tecnicos> tecnicos = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                especialidade
                FROM Tecnico
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                tecnicos.add(new Tecnicos(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade")
                ));
            }
            return tecnicos;
        }
    }
}
