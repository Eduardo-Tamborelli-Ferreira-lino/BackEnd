package org.example;

import org.example.connection.ConnectionFactory;
import org.example.model.*;
import org.example.service.CinemaService;
import org.example.service.CinemaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CinemaIntegrationTest {
    private CinemaService cinemaService;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        ConnectionFactory.initializeDatabase();
        connection = ConnectionFactory.getConnection();
        cinemaService = new CinemaServiceImpl();
    }

    private void insertCliente(int id, String nome, String email) throws Exception {
        String sql = "INSERT INTO Cliente (id, nome, email, ativo) VALUES (?, ?, ?, true)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.executeUpdate();
        }
    }

    private void insertFilme(int id, String titulo, String genero, String status) throws Exception {
        String sql = "INSERT INTO Filme (id, titulo, genero, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, titulo);
            stmt.setString(3, genero);
            stmt.setString(4, status);
            stmt.executeUpdate();
        }
    }

    private void insertReserva(int id, int cliente_id, int filme_id, LocalDate data_reserva, LocalDate data_sessao) throws Exception {
        String sql = "INSERT INTO Reserva (id, cliente_id, filme_id, data_reserva, data_sessao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, cliente_id);
            stmt.setInt(3, filme_id);
            stmt.setDate(4, java.sql.Date.valueOf(data_reserva));
            stmt.setDate(5, java.sql.Date.valueOf(data_sessao));
            stmt.executeUpdate();
        }
    }

    private String obterStatusFilme(int filme_id) throws Exception {
        String sql = "SELECT status FROM Filme WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, filme_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getString("status");
            }
        }
        return null;
    }

    private boolean isClienteAtivo(int cliente_id) throws Exception {
        String sql = "SELECT ativo FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cliente_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getBoolean("ativo");
            }
        }
        return false;
    }

    @Test
    public void testRegistrarCancelamentoCritico() throws Exception {
        insertCliente(1, "Carlos Dev", "carlos@dev.com");
        insertFilme(1, "Matrix 5", "Sci-Fi", "EM_CARTAZ");
        insertReserva(1, 1, 1, LocalDate.now(), LocalDate.now().plusDays(2));

        Cancelamento cancelamento = new Cancelamento(1, 1, "DANO_AO_EQUIPAMENTO", 150.0);
        cinemaService.registrarCancelamento(cancelamento);

        assertFalse("Cliente deveria estar inativo após dano ao equipamento", isClienteAtivo(1));
        assertEquals("MANUTENCAO", obterStatusFilme(1));
    }

    @Test
    public void testAtualizarReservaDataSessao() throws Exception {
        insertCliente(1, "Alice", "alice@email.com");
        insertFilme(1, "Inception", "Sci-Fi", "EM_CARTAZ");
        insertReserva(1, 1, 1, LocalDate.now(), LocalDate.now().plusDays(1));

        LocalDate novaData = LocalDate.now().plusDays(10);
        Reserva r = new Reserva();
        r.setId(1);
        r.setData_sessao(novaData);

        Reserva resultado = cinemaService.atualizarReserva(r);
        assertNotNull(resultado);
        assertEquals(novaData, resultado.getData_sessao());
    }

    @Test
    public void testExcluirClienteComReservaFutura() throws Exception {
        insertCliente(1, "Jose", "jose@email.com");
        insertFilme(1, "Avatar 3", "Ação", "EM_CARTAZ");
        insertReserva(1, 1, 1, LocalDate.now(), LocalDate.now().plusDays(5));

        assertThrows(RuntimeException.class, () -> {
            cinemaService.excluirCliente(1);
        });
    }

    @Test
    public void testGerarRelatorioCancelamentosCriticos() throws Exception {
        insertCliente(1, "Bob", "bob@email.com");
        insertFilme(1, "Titanic", "Drama", "EM_CARTAZ");
        insertReserva(1, 1, 1, LocalDate.now(), LocalDate.now());
        
        // Simular inserção de cancelamentos direto no banco
        String sql = "INSERT INTO Cancelamento (reserva_id, motivo, taxa_multa) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Desistiu");
            stmt.setDouble(3, 60.0); // Critico (> 50)
            stmt.executeUpdate();
            
            stmt.setInt(1, 1);
            stmt.setString(2, "Atrasou");
            stmt.setDouble(3, 20.0); // Não critico
            stmt.executeUpdate();
        }

        List<RelatorioCancelamento> relatorios = cinemaService.gerarRelatorioCancelamentosCriticos();
        assertEquals(1, relatorios.size());
        assertEquals("Bob", relatorios.get(0).getNomeCliente());
        assertTrue(relatorios.get(0).getTaxaMulta() > 50);
    }

    @Test
    public void testBuscarFilmesPorIds() throws Exception {
        insertFilme(1, "Filme 1", "Terror", "EM_CARTAZ");
        insertFilme(2, "Filme 2", "Comédia", "EM_CARTAZ");

        List<Filme> filmes = cinemaService.buscarFilmesPorIds(Arrays.asList(1, 2));
        assertEquals(2, filmes.size());
    }

    @Test
    public void testRegistrarCancelamentoReservaInexistente() {
        Cancelamento cancelamento = new Cancelamento(1, 999, "Teste", 10.0);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cinemaService.registrarCancelamento(cancelamento);
        });
        assertEquals("Alocação não encontrada ou inválida", exception.getMessage()); // Seguindo o padrão de erro de FK
    }

    @Test
    public void testAtualizarReservaInexistente() {
        Reserva r = new Reserva();
        r.setId(999);
        r.setData_sessao(LocalDate.now());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cinemaService.atualizarReserva(r);
        });
        assertEquals("Registro de reserva não localizado para atualização", exception.getMessage());
    }

    @Test
    public void testAtualizarReservaSemCampos() {
        Reserva r = new Reserva();
        r.setId(1); 
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cinemaService.atualizarReserva(r);
        });
        assertEquals("Registro de reserva não localizado para atualização", exception.getMessage());
    }

    @Test
    public void testExcluirClienteInexistente() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cinemaService.excluirCliente(999);
        });
        assertEquals("Cliente inexistente", exception.getMessage());
    }

    @Test
    public void testBuscarFilmesPorIdsVazio() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cinemaService.buscarFilmesPorIds(Arrays.asList());
        });
        assertEquals("A lista de IDs não pode estar vazia", exception.getMessage());
    }

    @Test
    public void testBuscarFilmesPorIdsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cinemaService.buscarFilmesPorIds(null);
        });
        assertEquals("A lista de IDs não pode estar vazia", exception.getMessage());
    }

    @Test
    public void testBuscarClientesPorNomeShortCircuit() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
            cinemaService.buscarClientesPorNome(null);
        });
        assertEquals("O termo de busca deve conter ao menos 3 caracteres", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
            cinemaService.buscarClientesPorNome("ab");
        });
        assertEquals("O termo de busca deve conter ao menos 3 caracteres", exception2.getMessage());
    }
}
