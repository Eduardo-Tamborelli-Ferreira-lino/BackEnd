package org.example.repository.Relatorio;

import java.sql.SQLException;
import java.util.ArrayList;

import org.example.model.RelatorioInfracao;

public interface RelatorioRepository {

    ArrayList<RelatorioInfracao> gerarRelatorio () throws SQLException;
}
