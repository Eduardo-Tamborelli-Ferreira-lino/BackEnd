package org.example.repository.Veiculo;

import java.sql.SQLException;

public interface VeiculoRepository {

    void alterarStatus(Integer veiculo_id) throws SQLException;
}
