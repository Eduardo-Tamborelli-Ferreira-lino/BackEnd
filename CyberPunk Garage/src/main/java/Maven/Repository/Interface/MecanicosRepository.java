package Maven.Repository.Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.Mecanicos;

public interface MecanicosRepository {

    Mecanicos save (Mecanicos mecanicos) throws SQLException;

    Mecanicos updateMecanicos (Mecanicos mecanicos ) throws SQLException;

    ArrayList<Mecanicos> findAll () throws SQLException;

    Mecanicos findById (int chosenId) throws SQLException;

    boolean delete (int chosenId) throws SQLException;

}
