package org.example.service;

import org.example.model.*;
import org.example.repository.Alocacao.AlocacaoRepositoryImpl;
import org.example.repository.Incidente.IncidenteRepositoryImpl;
import org.example.repository.Motorista.MotoristaRepositoryImpl;
import org.example.repository.Relatorio.RelatorioRepositoryImpl;
import org.example.repository.Veiculo.VeiculosRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FleetServiceImpl implements FleetService {

    private final AlocacaoRepositoryImpl ALOCACAO_REPOSITORY_IMPL = new AlocacaoRepositoryImpl();
    private final IncidenteRepositoryImpl INCIDENTE_REPOSITORY_IMPL = new IncidenteRepositoryImpl();
    private static final VeiculosRepositoryImpl VEICULOS_REPOSITORY_IMPL = new VeiculosRepositoryImpl();
    private static final MotoristaRepositoryImpl MOTORISTA_REPOSITORY_IMPL = new MotoristaRepositoryImpl();
    private static final RelatorioRepositoryImpl RELATORIO_REPOSITORY_IMPL = new RelatorioRepositoryImpl();

    @Override
    public Incidente registrarIncidente(Incidente incidente) throws SQLException {
        if (incidente.getGravidade().equals("ALTA")) {
            Integer veiculo_id = ALOCACAO_REPOSITORY_IMPL.buscarIdVeiculo(incidente);
            VEICULOS_REPOSITORY_IMPL.alterarStatus(veiculo_id);
            return INCIDENTE_REPOSITORY_IMPL.save(incidente);
        }
        return INCIDENTE_REPOSITORY_IMPL.save(incidente);
    }

    @Override
    public Alocacao atualizarAlocacao(Alocacao alocacao) throws SQLException {

        Alocacao alocacaoAntiga = ALOCACAO_REPOSITORY_IMPL.buscarPorId(alocacao.getId());

        if (alocacao.getData_fim() != null ) {
            Alocacao alocacaoAtualizada = ALOCACAO_REPOSITORY_IMPL.atualizarDataFim(alocacao);
            alocacaoAntiga.setData_fim(alocacaoAtualizada.getData_fim());
            return alocacaoAntiga;
        }

        else if (alocacao.getMotorista_id() != null ) {
            Alocacao alocacaoAtualizada = ALOCACAO_REPOSITORY_IMPL.atualizarMotorista(alocacao);
            alocacaoAntiga.setMotorista_id(alocacaoAtualizada.getMotorista_id());
            return alocacaoAntiga;
        }

        throw new RuntimeException("Registro de alocação não localizado para atualização");
    }

    @Override
    public void excluirMotorista(Integer id) throws SQLException {
        if (!ALOCACAO_REPOSITORY_IMPL.buscarAlocacaoMotorista(id)) {
            MOTORISTA_REPOSITORY_IMPL.excluirMotorista(id);
            return;
        }
        throw new RuntimeException("Motorista possui alocações ativas e não pode ser excluído");
    }

    @Override
    public List<RelatorioIncidente> relatorioIncidentes() throws SQLException {
        ArrayList<RelatorioIncidente> relatorio = RELATORIO_REPOSITORY_IMPL.relatorioIncidentes();
        List<RelatorioIncidente> relatoriosValidos = new ArrayList<>();
        for (RelatorioIncidente relatorioIncidente : relatorio) {
            if (relatorioIncidente.getGravidade().equals("MEDIA") || relatorioIncidente.getGravidade().equals("ALTA")) {
                relatoriosValidos.add(relatorioIncidente);
            }
        }
        return relatoriosValidos;
    }

    @Override
    public List<Veiculo> buscarVeiculosPorIds(List<Integer> ids) throws SQLException {
        return List.of();
    }

    @Override
    public List<Motorista> buscarMotoristasPorNome(String nome) throws SQLException {
        return List.of();
    }
}
