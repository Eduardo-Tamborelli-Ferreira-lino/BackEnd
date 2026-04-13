package Maven.Service.VeiculosService;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.Veiculos;
import Maven.Repository.VeiculosRepository.VeiculosRepositoryImpl;

public class VeiculosServiceImpl implements VeiculosService {

    private final VeiculosRepositoryImpl veiculosRepository;

    public VeiculosServiceImpl() {
        this.veiculosRepository = new VeiculosRepositoryImpl();
    }

    public VeiculosServiceImpl(VeiculosRepositoryImpl veiculosRepository) {
        this.veiculosRepository = veiculosRepository;
    }

    @Override
    public Veiculos save(Veiculos veiculos) throws SQLException {

        Veiculos veiculo = veiculosRepository.save(veiculos);

        return veiculo;
    }

    @Override
    public Veiculos updateVeiculos(Veiculos veiculos) throws SQLException {

        Veiculos veiculo = veiculosRepository.updateVeiculos(veiculos);

        return veiculo;
    }

    @Override
    public ArrayList<Veiculos> findAll() throws SQLException {

        ArrayList<Veiculos> veiculos = veiculosRepository.findAll();

        return veiculos;
    }

    @Override
    public Veiculos findById(int chosenId) throws SQLException {

        Veiculos veiculo = veiculosRepository.findById(chosenId);

        return veiculo;
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {

        boolean result = veiculosRepository.delete(chosenId);

        return result;
    }
}
