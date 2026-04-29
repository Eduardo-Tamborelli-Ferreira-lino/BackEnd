package org.example.service;

import org.example.model.*;
import org.example.repository.Emprestimo.EmprestimoRepositoryImpl;
import org.example.repository.Infracao.InfracaoRepositoryImpl;
import org.example.repository.Leitor.LeitorRepositoryImpl;
import org.example.repository.Livro.LivroRepositoryImpl;
import org.example.repository.Relatorio.RelatorioRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class LibraryServiceImpl implements LibraryService {

    private static final InfracaoRepositoryImpl INFRACAO_REPOSITORY_IMPL = new InfracaoRepositoryImpl();
    private static final LivroRepositoryImpl LIVRO_REPOSITORY_IMPL = new LivroRepositoryImpl();
    private static final EmprestimoRepositoryImpl EMPRESTIMO_REPOSITORY_IMPL = new EmprestimoRepositoryImpl();
    private static final LeitorRepositoryImpl LEITOR_REPOSITORY_IMPL = new LeitorRepositoryImpl();
    private static final RelatorioRepositoryImpl RELATORIO_REPOSITORY_IMPL = new RelatorioRepositoryImpl();

    @Override
    public Infracao registrarInfracao(Infracao infracao) throws SQLException {
        if (infracao.getGravidade().equals("ALTA")) {
            Emprestimo emprestimo = EMPRESTIMO_REPOSITORY_IMPL.buscarLivroId(infracao.getEmprestimo_id());
            LIVRO_REPOSITORY_IMPL.alterarStatus(emprestimo.getLivro_id());
        }
        return INFRACAO_REPOSITORY_IMPL.save(infracao);
    }

    @Override
    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {

        Emprestimo emprestimoAntigo = EMPRESTIMO_REPOSITORY_IMPL.buscarLivroId(emprestimo.getId());

        if (emprestimo.getData_devolucao() != null) {
            Emprestimo emprestimoNovo = EMPRESTIMO_REPOSITORY_IMPL.atualizarEmprestimoDataDevolucao(emprestimo);
            emprestimoAntigo.setData_devolucao(emprestimoNovo.getData_devolucao());
            return emprestimoAntigo;
        } else if (emprestimo.getLeitor_id() != null) {
            Emprestimo emprestimoNovo = EMPRESTIMO_REPOSITORY_IMPL.atualizarEmprestimoLeitorID(emprestimo);
            emprestimoAntigo.setLeitor_id(emprestimoNovo.getLeitor_id());
            return emprestimoAntigo;
        }
        throw new RuntimeException("Registro de empréstimo não localizado para atualização");
    }

    @Override
    public void excluirLeitor(Integer id) throws SQLException {

        if (EMPRESTIMO_REPOSITORY_IMPL.buscarEmprestimoLeitor(id)) {
            LEITOR_REPOSITORY_IMPL.excluirLeitor(id);
            return;
        }

        throw new RuntimeException("Leitor possui empréstimos ativos e não pode ser excluído");
    }

    @Override
    public List<RelatorioInfracao> gerarRelatorioInfracoes() throws SQLException {

        ArrayList<RelatorioInfracao> relatorio = new ArrayList<>();

        relatorio = RELATORIO_REPOSITORY_IMPL.gerarRelatorio();

        return relatorio;
    }

    @Override
    public List<Livro> buscarLivrosPorIds(List<Integer> ids) throws SQLException {

        ArrayList<Livro> livros = new ArrayList<>();

        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("A lista de IDs não pode estar vazia");
        }

        livros = LIVRO_REPOSITORY_IMPL.buscarLivros(ids);

        return livros;
    }

    @Override
    public List<Leitor> buscarLeitoresPorNome(String nome) throws SQLException {

        if (nome == null || nome.equals("") || nome.length() < 3) {
            throw new IllegalArgumentException("O termo de busca deve conter ao menos 3 caracteres");
        }

        List<Leitor> leitores = LEITOR_REPOSITORY_IMPL.buscarLeitorPorNome(nome);

        return leitores;
    }
}
