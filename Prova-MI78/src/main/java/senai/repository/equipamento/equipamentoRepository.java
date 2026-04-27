package senai.repository.equipamento;

import java.sql.SQLException;
import java.util.ArrayList;

import senai.model.Equipamento;

public interface equipamentoRepository {

    Equipamento save(Equipamento equipamento) throws SQLException;

    Equipamento findById(int chosenId) throws SQLException;

    ArrayList<Equipamento> findAll() throws SQLException;

    void update(int chosenId, int fornecedorId) throws SQLException;

    void delete(int chosenId) throws SQLException;
}
