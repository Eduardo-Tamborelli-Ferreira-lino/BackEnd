package org.example;

import org.example.connection.ConnectionFactory;
import org.example.model.*;
import org.example.service.LibraryService;
import org.example.service.LibraryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LibraryIntegrationTest {
    private LibraryService libraryService;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        ConnectionFactory.initializeDatabase();
        connection = ConnectionFactory.getConnection();
        libraryService = new LibraryServiceImpl();
    }

    private void insertLeitor(int id, String nome, String email) throws Exception {
        String sql = "INSERT INTO Leitor (id, nome, email, ativo) VALUES (?, ?, ?, true)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.executeUpdate();
        }
    }

    private void insertLivro(int id, String titulo, String isbn, String status) throws Exception {
        String sql = "INSERT INTO Livro (id, titulo, isbn, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, titulo);
            stmt.setString(3, isbn);
            stmt.setString(4, status);
            stmt.executeUpdate();
        }
    }

    private void insertEmprestimo(int id, int leitor_id, int livro_id, LocalDate data_emprestimo) throws Exception {
        String sql = "INSERT INTO Emprestimo (id, leitor_id, livro_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, leitor_id);
            stmt.setInt(3, livro_id);
            stmt.setDate(4, java.sql.Date.valueOf(data_emprestimo));
            stmt.executeUpdate();
        }
    }

    private String obterStatusLivro(int livro_id) throws Exception {
        String sql = "SELECT status FROM Livro WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, livro_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        }
        return null;
    }

    private int contarLeitores() throws Exception {
        String sql = "SELECT COUNT(*) FROM Leitor";
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
    public void testRegistrarInfracaoMedia() throws Exception {
        insertLeitor(1, "João Silva", "joao@email.com");
        insertLivro(1, "O Senhor dos Anéis", "123456", "EMPRESTADO");
        insertEmprestimo(1, 1, 1, LocalDate.of(2024, 1, 1));

        Infracao infracao = new Infracao(1, 1, "Devolução com atraso", "MEDIA");
        Infracao resultado = libraryService.registrarInfracao(infracao);

        assertNotNull(resultado.getId());
        assertEquals("EMPRESTADO", obterStatusLivro(1)); // Status não deve mudar
    }

    @Test
    public void testRegistrarInfracaoAlta() throws Exception {
        insertLeitor(1, "Maria Santos", "maria@email.com");
        insertLivro(1, "Dom Quixote", "654321", "EMPRESTADO");
        insertEmprestimo(1, 1, 1, LocalDate.of(2024, 1, 1));

        Infracao infracao = new Infracao(1, 1, "Livro molhado e rasgado", "ALTA");
        Infracao resultado = libraryService.registrarInfracao(infracao);

        assertNotNull(resultado.getId());
        assertEquals("MANUTENCAO", obterStatusLivro(1)); // Status deve mudar
    }

    @Test
    public void testRegistrarIncidenteAlocacaoInvalida() {
        Infracao infracao = new Infracao(999, "Infracao fake", "ALTA");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            libraryService.registrarInfracao(infracao);
        });

        assertEquals("Infração não encontrada ou inválida", exception.getMessage());
    }

    @Test
    public void testAtualizarEmprestimoDataDevolucao() throws Exception {
        insertLeitor(1, "Pedro Costa", "pedro@email.com");
        insertLivro(1, "O Pequeno Príncipe", "111111", "EMPRESTADO");
        insertEmprestimo(1, 1, 1, LocalDate.of(2024, 1, 1));

        LocalDate novaData = LocalDate.of(2024, 1, 31);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1);
        emprestimo.setData_devolucao(novaData);

        Emprestimo resultado = libraryService.atualizarEmprestimo(emprestimo);

        assertNotNull(resultado);
        assertEquals(Integer.valueOf(1), resultado.getId());
        assertEquals(Integer.valueOf(1), resultado.getLeitor_id());
        assertEquals(novaData, resultado.getData_devolucao());
    }

    @Test
    public void testAtualizarEmprestimoLeitor() throws Exception {
        insertLeitor(1, "Ana Lima", "ana@email.com");
        insertLeitor(2, "Bruno Oliveira", "bruno@email.com");
        insertLivro(1, "1984", "222222", "EMPRESTADO");
        insertEmprestimo(1, 1, 1, LocalDate.of(2024, 1, 1));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1);
        emprestimo.setLeitor_id(2);

        Emprestimo resultado = libraryService.atualizarEmprestimo(emprestimo);

        assertNotNull(resultado);
        assertEquals(Integer.valueOf(1), resultado.getId());
        assertEquals(Integer.valueOf(2), resultado.getLeitor_id());
    }

    @Test
    public void testAtualizarEmprestimoInvalido() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(999);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            libraryService.atualizarEmprestimo(emprestimo);
        });

        assertEquals("Registro de empréstimo não localizado para atualização", exception.getMessage());
    }

    @Test
    public void testExcluirLeitor() throws Exception {
        insertLeitor(1, "Carlos Mendes", "carlos@email.com");

        libraryService.excluirLeitor(1);

        assertEquals(0, contarLeitores());
    }

    @Test
    public void testExcluirLeitorComEmprestimo() throws Exception {
        insertLeitor(1, "Diana Ferreira", "diana@email.com");
        insertLivro(1, "A Arte da Guerra", "333333", "EMPRESTADO");
        insertEmprestimo(1, 1, 1, LocalDate.of(2024, 1, 1));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            libraryService.excluirLeitor(1);
        });

        assertEquals("Leitor possui empréstimos ativos e não pode ser excluído", exception.getMessage());
    }

    @Test
    public void testGerarRelatorioInfracoes() throws Exception {
        insertLeitor(1, "Eva Garcia", "eva@email.com");
        insertLivro(1, "Harry Potter", "444444", "EMPRESTADO");
        insertEmprestimo(1, 1, 1, LocalDate.of(2024, 1, 1));

        String sql = "INSERT INTO Infracao (emprestimo_id, descricao, gravidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Atraso de 5 dias");
            stmt.setString(3, "MEDIA");
            stmt.executeUpdate();
        }

        List<RelatorioInfracao> relatorios = libraryService.gerarRelatorioInfracoes();

        assertEquals(1, relatorios.size());

        RelatorioInfracao relatorio = relatorios.get(0);
        assertEquals("Eva Garcia", relatorio.getNomeLeitor());
        assertEquals("eva@email.com", relatorio.getEmail());
        assertEquals("Harry Potter", relatorio.getTituloLivro());
        assertEquals("Atraso de 5 dias", relatorio.getDescricaoInfracao());
        assertEquals("MEDIA", relatorio.getGravidade());
    }

    @Test
    public void testGerarRelatorioInfracoesFiltroGravidade() throws Exception {
        insertLeitor(1, "Frank Silva", "frank@email.com");
        insertLivro(1, "A Revolução dos Bichos", "555555", "EMPRESTADO");
        insertEmprestimo(1, 1, 1, LocalDate.of(2024, 1, 1));

        String sql = "INSERT INTO Infracao (emprestimo_id, descricao, gravidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Atraso de 1 dia");
            stmt.setString(3, "BAIXA");
            stmt.executeUpdate();
        }

        List<RelatorioInfracao> relatorios = libraryService.gerarRelatorioInfracoes();

        assertEquals(0, relatorios.size());
    }

    @Test
    public void testBuscarLivrosPorIds() throws Exception {
        insertLivro(1, "Livro A", "AAA", "DISPONIVEL");
        insertLivro(2, "Livro B", "BBB", "DISPONIVEL");

        List<Livro> livros = libraryService.buscarLivrosPorIds(Arrays.asList(1, 2));

        assertEquals(2, livros.size());
    }

    @Test
    public void testBuscarLivrosPorIdsVazio() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            libraryService.buscarLivrosPorIds(Arrays.asList());
        });

        assertEquals("A lista de IDs não pode estar vazia", exception.getMessage());
    }

    @Test
    public void testBuscarLivrosPorIdsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            libraryService.buscarLivrosPorIds(null);
        });

        assertEquals("A lista de IDs não pode estar vazia", exception.getMessage());
    }

    @Test
    public void testBuscarLeitoresPorNome() throws Exception {
        insertLeitor(1, "Gabriela Alves", "gabi@email.com");
        insertLeitor(2, "Gabriel Santos", "biel@email.com");

        List<Leitor> leitores = libraryService.buscarLeitoresPorNome("Gab");

        assertEquals(2, leitores.size());
    }

    @Test
    public void testBuscarLeitoresPorNomeMenor3Caracteres() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            libraryService.buscarLeitoresPorNome("ab");
        });

        assertEquals("O termo de busca deve conter ao menos 3 caracteres", exception.getMessage());
    }

    @Test
    public void testBuscarLeitoresPorNomeNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            libraryService.buscarLeitoresPorNome(null);
        });

        assertEquals("O termo de busca deve conter ao menos 3 caracteres", exception.getMessage());
    }
}
