package org.example.repository.Motorista;

import java.sql.SQLException;

public interface MotoristaRepository {

    void excluirMotorista (Integer id) throws SQLException;
    
}
