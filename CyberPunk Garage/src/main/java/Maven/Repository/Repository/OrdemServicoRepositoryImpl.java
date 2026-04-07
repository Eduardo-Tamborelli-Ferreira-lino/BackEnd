package Maven.Repository.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    public OrdemServico updateCliente(OrdemServico ordemServico) throws SQLException {
        String command = """
                UPDATE ordem_servico
                SET 
                nome = ?,
                email = ?
                WHERE id = ?
                """;

        try (Connection conn = ConnectionFactory.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, ordemServico.getDescricao());
            stmt.setDate(2, Date.valueOf(ordemServico.getDataEntrega()));
            stmt.setString(3, ordemServico.getStatus());
            stmt.setInt(4, ordemServico.getVeiculo());
            stmt.setInt(5, ordemServico.getMecanico());

            stmt.executeUpdate();

            return ordemServico;
        }
    }

    @Override
    public ArrayList<OrdemServico> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public OrdemServico findById(int chosenId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
