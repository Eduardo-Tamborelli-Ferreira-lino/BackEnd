package Maven.Repository.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Maven.Model.Cliente;
import Maven.Model.OrdemServico;
import Maven.Repository.Interface.OrdemServicoRepository;
import Maven.Util.ConnectionFactory;

public class OrdemServicoRepositoryImpl implements OrdemServicoRepository{

    @Override
    public OrdemServico save(OrdemServico ordemServico) throws SQLException {

        String command = """
                INSERT INTO ordem_servico(
                descricao,
                data_entrega,
                status,
                veiculo_id,
                mecanico_id)
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement
        (command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, ordemServico.getDescricao());
            stmt.setDate(2, Date.valueOf(ordemServico.getDataEntrega()));
            stmt.setString(3, ordemServico.getStatus());
            stmt.setInt(4, ordemServico.getVeiculo());
            stmt.setInt(5, ordemServico.getMecanico());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                
                ordemServico.setId(rs.getInt(1));

                return ordemServico;
            }
            else {
                
                throw new SQLException("Erro ao cadastrar cliente. ");

            }
        }
    }

    @Override
    public OrdemServico updateOrdemServico(OrdemServico ordemServico) throws SQLException {
        
        String command = """
                UPDATE ordem_servico
                SET 
                descricao = ?,
                dataEntrega = ?,
                status = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, ordemServico.getDescricao());
            stmt.setDate(2, Date.valueOf(ordemServico.getDataEntrega()));
            stmt.setString(3, ordemServico.getStatus());

            stmt.executeUpdate();

            return ordemServico;
        }
    }

    @Override
    public ArrayList<OrdemServico> findAll() throws SQLException {

        ArrayList<OrdemServico> ordemServicos = new ArrayList<>();

        String command = """
                SELECT
                id,
                descricao,
                data_entrega,
                status,
                veiculo_id,
                mecanico_id
                FROM ordem_servico
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                var ordemServico = new OrdemServico(
                    rs.getInt("id"),
                    rs.getString("descricao"),
                    rs.getObject("data_entrega", LocalDate.class),
                    rs.getString("Status"),
                    rs.getInt("veiculo_id"),
                    rs.getInt("mecanico_id")
                );

                ordemServicos.add(ordemServico);

            }

            return ordemServicos;
        }
    }

    @Override
    public OrdemServico findById(int chosenId) throws SQLException {

        OrdemServico ordemServico;

        String command = """
                SELECT
                id,
                descricao,
                data_entrega,
                status,
                veiculo_id,
                mecanico_id
                FROM ordem_servico
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, chosenId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                var findOrdemServico = new OrdemServico(
                    rs.getInt("id"),
                    rs.getString("descricao"),
                    rs.getObject("data_entrega", LocalDate.class),
                    rs.getString("Status"),
                    rs.getInt("veiculo_id"),
                    rs.getInt("mecanico_id")
                );

                ordemServico = findOrdemServico;

                return ordemServico;

            }
            else {

                throw new SQLException("Cliente não encontrado. ");

            }
        }
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {
        String command = """
                DELETE FROM ordem_servico
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
