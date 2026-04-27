package senai.service.fornecedor;

import senai.model.Fornecedor;
import senai.repository.fornecedor.FornecedorRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepositoryImpl F_REPOSITORY_IMPL = new FornecedorRepositoryImpl();

    @Override
    public Fornecedor criarFornecedor(Fornecedor fornecedor) throws SQLException {
        if (fornecedor.getNome() == null || fornecedor.getNome().isEmpty() ||
                fornecedor.getCnpj() == null || fornecedor.getCnpj().isEmpty()) {
            throw new NullPointerException("Não é permitido dados nulos.");
        }
        Fornecedor fornecedor2 = F_REPOSITORY_IMPL.save(fornecedor);
        return fornecedor2;
    }

    @Override
    public Fornecedor buscarPorId(int idGerado) throws SQLException {
        ArrayList<Fornecedor> fornecedores = F_REPOSITORY_IMPL.findAll();

        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == idGerado) {
                Fornecedor findFornecedor = F_REPOSITORY_IMPL.findById(idGerado);
                return findFornecedor;
            }
        }
        throw new RuntimeException("Id do Fornecedor não encontrado!");
    }

    @Override
    public ArrayList<Fornecedor> buscarTodos() throws SQLException {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        fornecedores = F_REPOSITORY_IMPL.findAll();
        return fornecedores;
    }

    @Override
    public void atualizarFornecedor(Fornecedor atualizado) throws SQLException {
        F_REPOSITORY_IMPL.update(atualizado);
    }

    @Override
    public void deletarFornecedor(int idGerado) throws SQLException {
        F_REPOSITORY_IMPL.delete(idGerado);
    }
}
