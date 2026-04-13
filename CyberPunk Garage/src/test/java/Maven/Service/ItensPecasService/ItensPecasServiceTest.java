package Maven.Service.ItensPecasService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Maven.Model.ItensPecas;
import Maven.Repository.ItensPecasRepository.ItensPecasRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class ItensPecasServiceTest {

    @Mock
    private ItensPecasRepositoryImpl itensPecasRepository;

    private ItensPecasServiceImpl itensPecasService;

    @BeforeEach
    void setUp() {
        itensPecasService = new ItensPecasServiceImpl(itensPecasRepository);
    }

    @Test
    void testSaveSuccess() throws SQLException {
        ItensPecas item = new ItensPecas("Turbocharger", 1500.0, 1, 1);
        when(itensPecasRepository.save(item)).thenReturn(item);

        ItensPecas result = itensPecasService.save(item);

        assertNotNull(result);
        assertEquals("Turbocharger", result.getNome_peca());
        verify(itensPecasRepository).save(item);
    }

    @Test
    void testSaveFailNullName() {
        ItensPecas item = new ItensPecas(null, 1500.0, 1, 1);
        assertThrows(NullPointerException.class, () -> itensPecasService.save(item));
    }

    @Test
    void testSaveFailNegativeValues() {
        ItensPecas item = new ItensPecas("Turbo", -10.0, 1, 1);
        assertThrows(RuntimeException.class, () -> itensPecasService.save(item));
    }

    @Test
    void testFindAll() throws SQLException {
        ArrayList<ItensPecas> list = new ArrayList<>();
        list.add(new ItensPecas(1, "Turbocharger", 1500.0, 1, 1));
        when(itensPecasRepository.findAll()).thenReturn(list);

        ArrayList<ItensPecas> result = itensPecasService.findAll();

        assertEquals(1, result.size());
        verify(itensPecasRepository).findAll();
    }

    @Test
    void testFindById() throws SQLException {
        ItensPecas item = new ItensPecas(1, "Turbocharger", 1500.0, 1, 1);
        when(itensPecasRepository.findById(1)).thenReturn(item);

        ItensPecas result = itensPecasService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(itensPecasRepository).findById(1);
    }

    @Test
    void testDelete() throws SQLException {
        when(itensPecasRepository.delete(1)).thenReturn(true);

        boolean result = itensPecasService.delete(1);

        assertTrue(result);
        verify(itensPecasRepository).delete(1);
    }
}
