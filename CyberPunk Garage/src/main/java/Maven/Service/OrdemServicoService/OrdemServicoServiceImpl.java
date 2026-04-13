package Maven.Service.OrdemServicoService;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.OrdemServico;
import Maven.Repository.OrdemServicoRepository.OrdemServicoRepositoryImpl;

public class OrdemServicoServiceImpl implements OrdemServicoService {

    private final OrdemServicoRepositoryImpl ordemServicoRepository;

    public OrdemServicoServiceImpl() {
        this.ordemServicoRepository = new OrdemServicoRepositoryImpl();
    }

    public OrdemServicoServiceImpl(OrdemServicoRepositoryImpl ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    @Override
    public OrdemServico save(OrdemServico ordemServico) throws SQLException {

        OrdemServico servico = ordemServicoRepository.save(ordemServico);

        return servico;

    }

    @Override
    public OrdemServico updateOrdemServico(OrdemServico ordemServico) throws SQLException {

        OrdemServico servico = ordemServicoRepository.updateOrdemServico(ordemServico);

        return servico;
    }

    @Override
    public ArrayList<OrdemServico> findAll() throws SQLException {

        ArrayList<OrdemServico> servicos = ordemServicoRepository.findAll();

        return servicos;
    }

    @Override
    public OrdemServico findById(int chosenId) throws SQLException {

        OrdemServico servico = ordemServicoRepository.findById(chosenId);

        return servico;
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {

        boolean result = ordemServicoRepository.delete(chosenId);

        return result;
    }
}
