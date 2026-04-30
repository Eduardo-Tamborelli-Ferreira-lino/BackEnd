package org.example.repository.Cliente;

import java.sql.SQLException;
import java.util.List;

import org.example.model.Cliente;

public interface ClienteRepository {
    
    void alterarAtivo(Integer id )throws SQLException;

    void excluirCliente(Integer id ) throws SQLException;

    List<Cliente> buscarClientesPorNome (String nome) throws SQLException;
}
