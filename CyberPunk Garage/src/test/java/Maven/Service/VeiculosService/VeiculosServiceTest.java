package Maven.Service.VeiculosService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Maven.Model.Veiculos;
import Maven.Repository.VeiculosRepository.VeiculosRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class VeiculosServiceTest {

    @Mock
    private VeiculosRepositoryImpl veiculosRepository;

    private VeiculosServiceImpl veiculosService;

    @BeforeEach
    void setUp() {
        veiculosService = new VeiculosServiceImpl(veiculosRepository);
    }

    @Test
    void testSave() throws SQLException {
        Veiculos veiculo = new Veiculos("Skyline R34", "CPK-2077", 1);
        when(veiculosRepository.save(veiculo)).thenReturn(veiculo);

        Veiculos result = veiculosService.save(veiculo);

        assertNotNull(result);
        assertEquals("Skyline R34", result.getModelo());
        verify(veiculosRepository).save(veiculo);
    }

    @Test
    void testFindAll() throws SQLException {
        ArrayList<Veiculos> list = new ArrayList<>();
        list.add(new Veiculos(1, "Skyline R34", "CPK-2077", 1));
        when(veiculosRepository.findAll()).thenReturn(list);

        ArrayList<Veiculos> result = veiculosService.findAll();

        assertEquals(1, result.size());
        verify(veiculosRepository).findAll();
    }

    @Test
    void testFindById() throws SQLException {
        Veiculos veiculo = new Veiculos(1, "Skyline R34", "CPK-2077", 1);
        when(veiculosRepository.findById(1)).thenReturn(veiculo);

        Veiculos result = veiculosService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(veiculosRepository).findById(1);
    }

    @Test
    void testDelete() throws SQLException {
        when(veiculosRepository.delete(1)).thenReturn(true);

        boolean result = veiculosService.delete(1);

        assertTrue(result);
        verify(veiculosRepository).delete(1);
    }
}
