package Maven.Service.OrdemServicoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Maven.Model.OrdemServico;
import Maven.Repository.OrdemServicoRepository.OrdemServicoRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class OrdemServicoServiceTest {

    @Mock
    private OrdemServicoRepositoryImpl ordemServicoRepository;

    private OrdemServicoServiceImpl ordemServicoService;

    @BeforeEach
    void setUp() {
        ordemServicoService = new OrdemServicoServiceImpl(ordemServicoRepository);
    }

    @Test
    void testSave() throws SQLException {
        OrdemServico os = new OrdemServico("Reparo", LocalDate.now(), "ABERTO", 1, 1);
        when(ordemServicoRepository.save(os)).thenReturn(os);

        OrdemServico result = ordemServicoService.save(os);

        assertNotNull(result);
        assertEquals("Reparo", result.getDescricao());
        verify(ordemServicoRepository).save(os);
    }

    @Test
    void testFindAll() throws SQLException {
        ArrayList<OrdemServico> list = new ArrayList<>();
        list.add(new OrdemServico(1, "Reparo", LocalDate.now(), "ABERTO", 1, 1));
        when(ordemServicoRepository.findAll()).thenReturn(list);

        ArrayList<OrdemServico> result = ordemServicoService.findAll();

        assertEquals(1, result.size());
        verify(ordemServicoRepository).findAll();
    }

    @Test
    void testFindById() throws SQLException {
        OrdemServico os = new OrdemServico(1, "Reparo", LocalDate.now(), "ABERTO", 1, 1);
        when(ordemServicoRepository.findById(1)).thenReturn(os);

        OrdemServico result = ordemServicoService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(ordemServicoRepository).findById(1);
    }

    @Test
    void testDelete() throws SQLException {
        when(ordemServicoRepository.delete(1)).thenReturn(true);

        boolean result = ordemServicoService.delete(1);

        assertTrue(result);
        verify(ordemServicoRepository).delete(1);
    }
}
