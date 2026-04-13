package Maven.Service.MecanicosService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Maven.Model.Mecanicos;
import Maven.Repository.MecanicosRepository.MecanicosRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class MecanicoServiceTest {

    @Mock
    private MecanicosRepositoryImpl mecanicosRepository;

    private MecanicoServiceImpl mecanicoService;

    @BeforeEach
    void setUp() {
        mecanicoService = new MecanicoServiceImpl(mecanicosRepository);
    }

    @Test
    void testSave() throws SQLException {
        Mecanicos mecanico = new Mecanicos("Mecanico 1", "Motor", 100.0);
        when(mecanicosRepository.save(mecanico)).thenReturn(mecanico);

        Mecanicos result = mecanicoService.save(mecanico);

        assertNotNull(result);
        assertEquals("Mecanico 1", result.getNome());
        verify(mecanicosRepository).save(mecanico);
    }

    @Test
    void testFindAll() throws SQLException {
        ArrayList<Mecanicos> list = new ArrayList<>();
        list.add(new Mecanicos(1, "Mecanico 1", "Motor", 100.0));
        when(mecanicosRepository.findAll()).thenReturn(list);

        ArrayList<Mecanicos> result = mecanicoService.findAll();

        assertEquals(1, result.size());
        verify(mecanicosRepository).findAll();
    }

    @Test
    void testFindById() throws SQLException {
        Mecanicos mecanico = new Mecanicos(1, "Mecanico 1", "Motor", 100.0);
        when(mecanicosRepository.findById(1)).thenReturn(mecanico);

        Mecanicos result = mecanicoService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(mecanicosRepository).findById(1);
    }

    @Test
    void testDelete() throws SQLException {
        when(mecanicosRepository.delete(1)).thenReturn(true);

        boolean result = mecanicoService.delete(1);

        assertTrue(result);
        verify(mecanicosRepository).delete(1);
    }
}
