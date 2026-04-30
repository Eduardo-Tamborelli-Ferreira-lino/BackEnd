package org.example.repository.RelatorioCancelamento;

import java.sql.SQLException;
import java.util.List;

import org.example.model.RelatorioCancelamento;

public interface RelatorioRepository {

    List<RelatorioCancelamento> gerarRelatorioCancelamentos() throws SQLException;
}
