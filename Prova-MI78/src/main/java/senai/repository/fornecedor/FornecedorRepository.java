package senai.repository.fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;

import senai.model.Fornecedor;

public interface FornecedorRepository {

    Fornecedor save(Fornecedor fornecedor) throws SQLException;

    Fornecedor findById(int idGerado) throws SQLException;

    ArrayList<Fornecedor> findAll() throws SQLException;

    void update(Fornecedor atualizado) throws SQLException;

    void delete(int idGerado) throws SQLException;
}
