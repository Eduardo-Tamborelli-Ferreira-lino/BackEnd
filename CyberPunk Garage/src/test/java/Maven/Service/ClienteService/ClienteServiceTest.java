package Maven.Service.ClienteService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Maven.Model.Cliente;
import Maven.Repository.ClienteRepository.ClienteRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepositoryImpl clienteRepository;

    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    void testSaveSuccess() throws SQLException {
        Cliente cliente = new Cliente("Test User", "test@example.com");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.save(cliente);

        assertNotNull(result);
        assertEquals("Test User", result.getNome());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void testSaveFailNullName() {
        Cliente cliente = new Cliente(null, "test@example.com");
        assertThrows(NullPointerException.class, () -> clienteService.save(cliente));
    }

    @Test
    void testFindAll() throws SQLException {
        ArrayList<Cliente> list = new ArrayList<>();
        list.add(new Cliente(1, "User 1", "email1@test.com"));
        when(clienteRepository.findAll()).thenReturn(list);

        ArrayList<Cliente> result = clienteService.findAll();

        assertEquals(1, result.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void testFindByIdSuccess() throws SQLException {
        ArrayList<Cliente> list = new ArrayList<>();
        Cliente cliente = new Cliente(1, "User 1", "email1@test.com");
        list.add(cliente);
        when(clienteRepository.findAll()).thenReturn(list);
        when(clienteRepository.findById(1)).thenReturn(cliente);

        Cliente result = clienteService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindByIdNotFound() throws SQLException {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> clienteService.findById(99));
    }

    @Test
    void testDeleteSuccess() throws SQLException {
        ArrayList<Cliente> list = new ArrayList<>();
        list.add(new Cliente(1, "User 1", "email1@test.com"));
        when(clienteRepository.findAll()).thenReturn(list);
        when(clienteRepository.delete(1)).thenReturn(true);

        boolean result = clienteService.delete(1, "Ana Beatriz");

        assertTrue(result);
        verify(clienteRepository).delete(1);
    }

    @Test
    void testDeleteWrongKeyword() throws SQLException {
        boolean result = clienteService.delete(1, "Invalid");
        assertFalse(result);
    }
}
