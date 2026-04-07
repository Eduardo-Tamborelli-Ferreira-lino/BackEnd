package Maven.Repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Maven.Model.Mecanicos;
import Maven.Repository.Interface.MecanicosRepository;
import Maven.Util.ConnectionFactory;

public class MecanicosRepositoryImpl implements MecanicosRepository{

    @Override
    public Mecanicos save(Mecanicos mecanicos) throws SQLException {
        String command = """
                INSERT INTO mecanicos(
                nome,
                especialidade,
                valor_hora)
                VALUES
                (?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement
        (command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, mecanicos.getNome());
            stmt.setString(2, mecanicos.getEspecialidade());
            stmt.setDouble(3, mecanicos.getValorHora());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                
                mecanicos.setId(rs.getInt(1));

                return mecanicos;
            }
            else {
                
                throw new SQLException("Erro ao cadastrar Motorista. ");

            }
        }
    }

    @Override
    public Mecanicos updateMecanicos (Mecanicos mecanicos) throws SQLException {
        String command = """
                UPDATE mecanicos
                SET 
                nome = ?,
                especialidade = ?,
                valor_hora = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, mecanicos.getNome());
            stmt.setString(2, mecanicos.getEspecialidade());
            stmt.setDouble(3, mecanicos.getValorHora());

            stmt.executeUpdate();

            return mecanicos;
        }
    }

    @Override
    public ArrayList<Mecanicos> findAll() throws SQLException {

        ArrayList<Mecanicos> mecanicos = new ArrayList<>();

        String command = """
                SELECT
                id,
                nome,
                especialidade,
                valor_hora
                FROM mecanicos
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                var mecanico = new Mecanicos(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("especialidade"),
                    rs.getDouble("valor_hora")
                );

                mecanicos.add(mecanico);

            }

            return mecanicos;
        }
    }

    @Override
    public Mecanicos findById(int chosenId) throws SQLException {

        Mecanicos mecanicos;

        String command = """
                SELECT
                id,
                nome,
                email
                FROM mecanicos
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                var findMotorista = new Mecanicos(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("especialidade"),
                    rs.getDouble("valor_hora")
                );

                mecanicos = findMotorista;

                return mecanicos;

            }
            else {

                throw new SQLException("Motorista não encontrado. ");

            }
        }
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {

        String command = """
                DELETE FROM mecanicos
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
