package org.example.service;

import org.example.model.*;
import org.example.repository.Cancelamento.CancelamentoRepositoryImpl;
import org.example.repository.Cliente.ClienteRepositoryImpl;
import org.example.repository.Filme.FilmeRepositoryImpl;
import org.example.repository.RelatorioCancelamento.RelatorioRepositoryImpl;
import org.example.repository.Reserva.ReservaRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class CinemaServiceImpl implements CinemaService {

    private static final CancelamentoRepositoryImpl CANCELAMENTO_REPOSITORY_IMPL = new CancelamentoRepositoryImpl();
    private static final ReservaRepositoryImpl RESERVA_REPOSITORY_IMPL = new ReservaRepositoryImpl();
    private static final FilmeRepositoryImpl FILME_REPOSITORY_IMPL = new FilmeRepositoryImpl();
    private static final ClienteRepositoryImpl CLIENTE_REPOSITORY_IMPL = new ClienteRepositoryImpl();
    private static final RelatorioRepositoryImpl RELATORIO_REPOSITORY_IMPL = new RelatorioRepositoryImpl();

    @Override
    public Cancelamento registrarCancelamento(Cancelamento cancelamento) throws SQLException {

        if (cancelamento.getMotivo().equals("DANO_AO_EQUIPAMENTO")) {
            Reserva reserva = RESERVA_REPOSITORY_IMPL.buscarReservaClienteFilme(cancelamento.getReserva_id());
            FILME_REPOSITORY_IMPL.alterarStatus(reserva.getFilme_id());
            CLIENTE_REPOSITORY_IMPL.alterarAtivo(reserva.getCliente_id());
        }
        return CANCELAMENTO_REPOSITORY_IMPL.registrarCancelamento(cancelamento);
    }

    @Override
    public Reserva atualizarReserva(Reserva reserva) throws SQLException {

        Reserva reservaAntiga = RESERVA_REPOSITORY_IMPL.buscarReservaId(reserva.getId());

        if (reserva.getCliente_id() != null) {
            Reserva reservaAtualizada = RESERVA_REPOSITORY_IMPL.atualizarReservaCliente(reserva);
            reservaAntiga.setCliente_id(reservaAtualizada.getCliente_id());
            return reservaAntiga;
        } else if (reserva.getData_sessao() != null) {
            Reserva reservaAtualizada = RESERVA_REPOSITORY_IMPL.atualizarReservaDataSessao(reserva);
            reservaAntiga.setData_sessao(reservaAtualizada.getData_sessao());
            return reservaAntiga;
        }

        throw new RuntimeException("Registro de reserva não localizado para atualização");
    }

    @Override
    public void excluirCliente(Integer id) throws SQLException {
        
        if (RESERVA_REPOSITORY_IMPL.buscarClienteReserva(id)) {
            CLIENTE_REPOSITORY_IMPL.excluirCliente(id);
            return;
        }

        throw new RuntimeException("Cliente inexistente");
    }

    @Override
    public List<RelatorioCancelamento> gerarRelatorioCancelamentosCriticos() throws SQLException {
        
        List<RelatorioCancelamento> relatorio = RELATORIO_REPOSITORY_IMPL.gerarRelatorioCancelamentos();

        return relatorio;
    }

    @Override
    public List<Filme> buscarFilmesPorIds(List<Integer> ids) throws SQLException {
        
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("A lista de IDs não pode estar vazia");
        }

        List<Filme> filmes = FILME_REPOSITORY_IMPL.buscarFilmes(ids);

        return filmes;
    }

    @Override
    public List<Cliente> buscarClientesPorNome(String nome) throws SQLException {

        if (nome == null || nome.isEmpty() || nome.length() < 3) {
            throw new IllegalArgumentException("O termo de busca deve conter ao menos 3 caracteres");
        }

        List<Cliente> clientes = CLIENTE_REPOSITORY_IMPL.buscarClientesPorNome(nome);

        return clientes;
    }
}
