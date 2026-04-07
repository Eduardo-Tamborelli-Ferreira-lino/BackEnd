package Maven.Repository.Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.OrdemServico;

public interface OrdemServicoRepository {

    OrdemServico save (OrdemServico ordemServico) throws SQLException;

    OrdemServico updateCliente (OrdemServico ordemServico ) throws SQLException;

    ArrayList<OrdemServico> findAll () throws SQLException;

    OrdemServico findById (int chosenId) throws SQLException;

    boolean delete (int chosenId) throws SQLException;

}
