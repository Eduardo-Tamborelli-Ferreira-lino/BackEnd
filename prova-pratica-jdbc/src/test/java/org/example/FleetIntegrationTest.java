package org.example;

import org.example.connection.ConnectionFactory;
import org.example.model.*;
import org.example.service.FleetService;
import org.example.service.FleetServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FleetIntegrationTest {
    private FleetService fleetService;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        ConnectionFactory.initializeDatabase();
        connection = ConnectionFactory.getConnection();
        fleetService = new FleetServiceImpl();
    }

    private void insertMotorista(int id, String nome, String cnh) throws Exception {
        String sql = "INSERT INTO Motorista (id, nome, cnh, ativo) VALUES (?, ?, ?, true)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, cnh);
            stmt.executeUpdate();
        }
    }

    private void insertVeiculo(int id, String placa, String modelo, String status) throws Exception {
        String sql = "INSERT INTO Veiculo (id, placa, modelo, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, placa);
            stmt.setString(3, modelo);
            stmt.setString(4, status);
            stmt.executeUpdate();
        }
    }

    private void insertAlocacao(int id, int motorista_id, int veiculo_id, LocalDate data_inicio) throws Exception {
        String sql = "INSERT INTO Alocacao (id, motorista_id, veiculo_id, data_inicio, data_fim) VALUES (?, ?, ?, ?, NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, motorista_id);
            stmt.setInt(3, veiculo_id);
            stmt.setDate(4, java.sql.Date.valueOf(data_inicio));
            stmt.executeUpdate();
        }
    }

    private String obterStatusVeiculo(int veiculo_id) throws Exception {
        String sql = "SELECT status FROM Veiculo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, veiculo_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        }
        return null;
    }

    // private int contarIncidentes() throws Exception {
    //     String sql = "SELECT COUNT(*) FROM Incidente";
    //     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    //         try (ResultSet rs = stmt.executeQuery()) {
    //             if (rs.next()) {
    //                 return rs.getInt(1);
    //             }
    //         }
    //     }
    //     return 0;
    // }

    private int contarMotoristas() throws Exception {
        String sql = "SELECT COUNT(*) FROM Motorista";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    @Test
    public void testRegistrarIncidenteBaixa() throws Exception {
        insertMotorista(1, "João Silva", "CNH123456");
        insertVeiculo(1, "ABC1234", "Volvo FH", "DISPONIVEL");
        insertAlocacao(1, 1, 1, LocalDate.of(2024, 1, 1));

        Incidente incidente = new Incidente(1, "Pneu furado", "BAIXA", LocalDate.of(2024, 1, 15));
        Incidente resultado = fleetService.registrarIncidente(incidente);

        assertNotNull(resultado.getId());
        assertEquals("DISPONIVEL", obterStatusVeiculo(1));
    }

    @Test
    public void testRegistrarIncidenteAlta() throws Exception {
        insertMotorista(1, "Maria Santos", "CNH654321");
        insertVeiculo(1, "XYZ9999", "Scania", "DISPONIVEL");
        insertAlocacao(1, 1, 1, LocalDate.of(2024, 1, 1));

        Incidente incidente = new Incidente(1, "Colisão frontal", "ALTA", LocalDate.of(2024, 1, 20));
        Incidente resultado = fleetService.registrarIncidente(incidente);

        assertNotNull(resultado.getId());
        assertEquals("MANUTENCAO", obterStatusVeiculo(1));
    }

    @Test
    public void testRegistrarIncidenteAlocacaoInvalida() {
        Incidente incidente = new Incidente(999, "Incidente fake", "ALTA", LocalDate.of(2024, 1, 1));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fleetService.registrarIncidente(incidente);
        });

        assertEquals("Alocação não encontrada ou inválida", exception.getMessage());
    }

    @Test
    public void testAtualizarAlocacaoDataFim() throws Exception {
        insertMotorista(1, "Pedro Costa", "CNH111111");
        insertVeiculo(1, "DEF5555", "Mercedes", "DISPONIVEL");
        insertAlocacao(1, 1, 1, LocalDate.of(2024, 1, 1));

        LocalDate novaDataFim = LocalDate.of(2024, 1, 31);

        Alocacao alocacao = new Alocacao();
        alocacao.setId(1);
        alocacao.setData_fim(novaDataFim);

        Alocacao resultado = fleetService.atualizarAlocacao(alocacao);

        assertNotNull(resultado);
        assertEquals(Integer.valueOf(1), resultado.getId());
        assertEquals(Integer.valueOf(1), resultado.getMotorista_id());

        assertEquals(novaDataFim, resultado.getData_fim());
    }

    @Test
    public void testAtualizarAlocacaoMotorista() throws Exception {
        insertMotorista(1, "Ana Lima", "CNH222222");
        insertMotorista(2, "Bruno Oliveira", "CNH333333");
        insertVeiculo(1, "GHI6666", "Iveco", "DISPONIVEL");
        insertAlocacao(1, 1, 1, LocalDate.of(2024, 1, 1));

        int novoMotoristaId = 2;

        Alocacao alocacao = new Alocacao();
        alocacao.setId(1);
        alocacao.setMotorista_id(novoMotoristaId);

        Alocacao resultado = fleetService.atualizarAlocacao(alocacao);

        assertNotNull(resultado);

        assertEquals(Integer.valueOf(1), resultado.getId());

        assertEquals(Integer.valueOf(novoMotoristaId), resultado.getMotorista_id());
    }

    @Test
    public void testAtualizarAlocacaoNaoEncontrada() {
        Alocacao alocacao = new Alocacao();
        alocacao.setId(999);
        alocacao.setData_fim(LocalDate.of(2024, 1, 31));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fleetService.atualizarAlocacao(alocacao);
        });

        assertEquals("Registro de alocação não localizado para atualização", exception.getMessage());
    }

    @Test
    public void testExcluirMotorista() throws Exception {
        insertMotorista(1, "Carlos Mendes", "CNH444444");

        fleetService.excluirMotorista(1);

        assertEquals(0, contarMotoristas());
    }

    @Test
    public void testExcluirMotoristaComAlocacao() throws Exception {
        insertMotorista(1, "Diana Ferreira", "CNH555555");
        insertVeiculo(1, "JKL7777", "DAF", "DISPONIVEL");
        insertAlocacao(1, 1, 1, LocalDate.of(2024, 1, 1));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fleetService.excluirMotorista(1);
        });

        assertEquals("Motorista possui alocações ativas e não pode ser excluído", exception.getMessage());
    }

    @Test
    public void testExcluirMotoristaInexistente() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fleetService.excluirMotorista(999);
        });

        assertEquals("Motorista inexistente", exception.getMessage());
    }

    @Test
    public void testRelatorioIncidentes() throws Exception {
        insertMotorista(1, "Eva Garcia", "CNH666666");
        insertVeiculo(1, "MNO8888", "Renault", "DISPONIVEL");
        insertAlocacao(1, 1, 1, LocalDate.of(2024, 1, 1));

        String sql = "INSERT INTO Incidente (alocacao_id, descricao, gravidade, data_incidente) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Colisão leve");
            stmt.setString(3, "MEDIA");
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.of(2024, 1, 10)));
            stmt.executeUpdate();
        }

        List<RelatorioIncidente> relatorios = fleetService.relatorioIncidentes();

        assertEquals(1, relatorios.size());

        RelatorioIncidente relatorio = relatorios.get(0);

        assertEquals("Eva Garcia", relatorio.getNomeMotorista());
        assertEquals("CNH666666", relatorio.getCnh());
        assertEquals("MNO8888", relatorio.getPlacaVeiculo());
        assertEquals("Colisão leve", relatorio.getDescricaoIncidente());
        assertEquals("MEDIA", relatorio.getGravidade());
    }

    @Test
    public void testRelatorioIncidentesFiltroGravidade() throws Exception {
        insertMotorista(1, "Frank Silva", "CNH777777");
        insertVeiculo(1, "PQR9999", "Volkswagen", "DISPONIVEL");
        insertAlocacao(1, 1, 1, LocalDate.of(2024, 1, 1));

        String sql = "INSERT INTO Incidente (alocacao_id, descricao, gravidade, data_incidente) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Pequeno amassado");
            stmt.setString(3, "BAIXA");
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.of(2024, 1, 10)));
            stmt.executeUpdate();
        }

        List<RelatorioIncidente> relatorios = fleetService.relatorioIncidentes();

        assertEquals(0, relatorios.size());
    }

    @Test
    public void testBuscarVeiculosPorIds() throws Exception {
        insertVeiculo(1, "STU0000", "Hyundai", "DISPONIVEL");
        insertVeiculo(2, "VWX1111", "Chevrolet", "DISPONIVEL");
        insertVeiculo(3,"teste","teste", "DISPONIVEL");

        List<Veiculo> veiculos = fleetService.buscarVeiculosPorIds(Arrays.asList(1,2));

        assertEquals(2, veiculos.size());
    }

    @Test
    public void testBuscarVeiculosPorIdsVazio() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fleetService.buscarVeiculosPorIds(Arrays.asList());
        });

        assertEquals("A lista de IDs não pode estar vazia", exception.getMessage());
    }

    @Test
    public void testBuscarVeiculosPorIdsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fleetService.buscarVeiculosPorIds(null);
        });

        assertEquals("A lista de IDs não pode estar vazia", exception.getMessage());
    }

    @Test
    public void testBuscarMotoristasPorNome() throws Exception {
        insertMotorista(1, "Gabriela Alves", "CNH888888");
        insertMotorista(2, "Gabriel Santos", "CNH999999");

        List<Motorista> motoristas = fleetService.buscarMotoristasPorNome("Gab");

        assertEquals(2, motoristas.size());

        Motorista motorista1 = motoristas.stream().filter(m -> m.getId() == 1).findFirst().orElse(null);
        Motorista motorista2 = motoristas.stream().filter(m -> m.getId() == 2).findFirst().orElse(null);

        assertNotNull(motorista1);
        assertNotNull(motorista2);

        assertEquals(Integer.valueOf(1), motorista1.getId());
        assertEquals("Gabriela Alves", motorista1.getNome());
        assertEquals("CNH888888", motorista1.getCnh());

        assertEquals(Integer.valueOf(2), motorista2.getId());
        assertEquals("Gabriel Santos", motorista2.getNome());
        assertEquals("CNH999999", motorista2.getCnh());
    }

    @Test
    public void testBuscarMotoristasPorNomeMenor3Caracteres() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fleetService.buscarMotoristasPorNome("ab");
        });

        assertEquals("O termo de busca deve conter ao menos 3 caracteres", exception.getMessage());
    }


    @Test
    public void testBuscarMotoristasPorNomeNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fleetService.buscarMotoristasPorNome(null);
        });

        assertEquals("O termo de busca deve conter ao menos 3 caracteres", exception.getMessage());
    }

    @Test
    public void testBuscarMotoristasPorNomeSemResultados() throws Exception {
        insertMotorista(1, "Heitor Costa", "CNH101010");

        List<Motorista> motoristas = fleetService.buscarMotoristasPorNome("xyz");

        assertEquals(0, motoristas.size());
    }
}
