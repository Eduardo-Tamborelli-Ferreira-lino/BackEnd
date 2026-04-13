package Maven.Repository.ClienteRepository;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.*;

public interface ClienteRepository {

    Cliente save(Cliente cliente) throws SQLException;

    Cliente updateCliente(Cliente cliente) throws SQLException;

    ArrayList<Cliente> findAll() throws SQLException;

    Cliente findById(int chosenId) throws SQLException;

    boolean delete(int chosenId) throws SQLException;

}
