package Maven.Service.MecanicosService;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.Mecanicos;
import Maven.Repository.MecanicosRepository.MecanicosRepositoryImpl;

public class MecanicoServiceImpl implements MecanicoService {

    private final MecanicosRepositoryImpl mecanicosRepository;

    public MecanicoServiceImpl() {
        this.mecanicosRepository = new MecanicosRepositoryImpl();
    }

    public MecanicoServiceImpl(MecanicosRepositoryImpl mecanicosRepository) {
        this.mecanicosRepository = mecanicosRepository;
    }

    @Override
    public Mecanicos save(Mecanicos mecanicos) throws SQLException {

        Mecanicos mecanico = mecanicosRepository.save(mecanicos);

        return mecanico;
    }

    @Override
    public Mecanicos updateMecanicos(Mecanicos mecanicos) throws SQLException {

        Mecanicos mecanico = mecanicosRepository.updateMecanicos(mecanicos);

        return mecanico;
    }

    @Override
    public ArrayList<Mecanicos> findAll() throws SQLException {

        ArrayList<Mecanicos> mecanicos = mecanicosRepository.findAll();

        return mecanicos;
    }

    @Override
    public Mecanicos findById(int chosenId) throws SQLException {

        Mecanicos mecanico = mecanicosRepository.findById(chosenId);

        return mecanico;
    }

    @Override
    public boolean delete(int chosenId) throws SQLException {

        boolean result = mecanicosRepository.delete(chosenId);

        return result;
    }

}
