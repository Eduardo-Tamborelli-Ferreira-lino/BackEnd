package Maven.Repository.VeiculosRepository;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.Veiculos;

public interface VeiculosRepository {

    Veiculos save(Veiculos veiculos) throws SQLException;

    Veiculos updateVeiculos(Veiculos veiculos) throws SQLException;

    ArrayList<Veiculos> findAll() throws SQLException;

    Veiculos findById(int chosenId) throws SQLException;

    boolean delete(int chosenId) throws SQLException;

}
