package org.example.repository.Veiculo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.model.Veiculo;

public interface VeiculoRepository {

    void alterarStatus(Integer veiculo_id) throws SQLException;

    ArrayList<Veiculo> buscarVeiculosIn(List<Integer> ids) throws SQLException;
}
