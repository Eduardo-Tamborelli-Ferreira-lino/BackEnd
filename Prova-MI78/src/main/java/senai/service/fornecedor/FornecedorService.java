package senai.service.fornecedor;

import senai.model.Fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FornecedorService {
    public Fornecedor criarFornecedor(Fornecedor fornecedor) throws SQLException;

    public Fornecedor buscarPorId(int idGerado) throws SQLException;

    public ArrayList<Fornecedor> buscarTodos() throws SQLException;

    public void atualizarFornecedor(Fornecedor atualizado) throws SQLException;

    public void deletarFornecedor(int idGerado) throws SQLException;
}
