package Maven.Service.ClienteService;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.Cliente;

public interface ClienteService {

    Cliente save(Cliente cliente) throws SQLException;

    Cliente updateCliente(Cliente cliente) throws SQLException;

    ArrayList<Cliente> findAll() throws SQLException;

    Cliente findById(int chosenId) throws SQLException;

    boolean delete(int chosenId, String keyWord) throws SQLException;

}
