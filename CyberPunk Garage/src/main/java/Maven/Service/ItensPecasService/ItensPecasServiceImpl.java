package Maven.Service.ItensPecasService;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.ItensPecas;
import Maven.Repository.ItensPecasRepository.ItensPecasRepositoryImpl;

public class ItensPecasServiceImpl implements ItensPecasService {

    private final ItensPecasRepositoryImpl itensPecasRepository;

    public ItensPecasServiceImpl() {
        this.itensPecasRepository = new ItensPecasRepositoryImpl();
    }

    public ItensPecasServiceImpl(ItensPecasRepositoryImpl itensPecasRepository) {
        this.itensPecasRepository = itensPecasRepository;
    }

    @Override
    public ItensPecas save(ItensPecas itensPecas) throws SQLException {

        if (itensPecas.getNome_peca() == null || itensPecas.getNome_peca().isEmpty()) {
            throw new NullPointerException("Não pode ser inserido valores nulos ou vazios.");
        }

        if (itensPecas.getPreco_unitario() < 0 || itensPecas.getQuantidade() < 0) {
            throw new RuntimeException("Not possible insert numbers negatives");
        }

        ItensPecas itemPeca = itensPecasRepository.save(itensPecas);

        return itemPeca;
    }

    @Override
    public ItensPecas updateItensPecas(ItensPecas itensPecas) throws SQLException {

        ItensPecas itemPeca = itensPecasRepository.updateItensPecas(itensPecas);

        return itemPeca;
    }

    @Override
    public ArrayList<ItensPecas> findAll() throws SQLException {

        ArrayList<ItensPecas> itemPeca = itensPecasRepository.findAll();

        return itemPeca;
    }

    @Override
    public ItensPecas findById(int chosenId) throws SQLException {

        ItensPecas itemPeca = itensPecasRepository.findById(chosenId);

        return itemPeca;
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {

        boolean result = itensPecasRepository.delete(chosenId);

        return result;

    }

}
