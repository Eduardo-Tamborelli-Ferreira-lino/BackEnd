package org.example.service;

import org.example.model.*;

import java.sql.SQLException;
import java.util.List;

public interface FleetService {
    Incidente registrarIncidente(Incidente incidente) throws SQLException;
    Alocacao atualizarAlocacao(Alocacao alocacao) throws SQLException;
    void excluirMotorista(Integer id) throws SQLException;
    List<RelatorioIncidente> relatorioIncidentes() throws SQLException;
    List<Veiculo> buscarVeiculosPorIds(List<Integer> ids) throws SQLException;
    List<Motorista> buscarMotoristasPorNome(String nome) throws SQLException;
}
