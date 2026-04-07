package Maven.Repository.Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.ItensPecas;

public interface ItensPecasRepository {

    ItensPecas save (ItensPecas itensPecas) throws SQLException;

    ItensPecas updateItensPecas (ItensPecas itensPecas ) throws SQLException;

    ArrayList<ItensPecas> findAll () throws SQLException;

    ItensPecas findById (int chosenId) throws SQLException;

    boolean delete (int chosenId) throws SQLException;
}
