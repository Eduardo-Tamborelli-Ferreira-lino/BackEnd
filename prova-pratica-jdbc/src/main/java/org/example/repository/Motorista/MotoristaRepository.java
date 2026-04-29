package org.example.repository.Motorista;

import java.sql.SQLException;
import java.util.ArrayList;

import org.example.model.Motorista;

public interface MotoristaRepository {

    void excluirMotorista (Integer id) throws SQLException;
    
    ArrayList<Motorista> buscarPorNome (String nome) throws SQLException;
}
