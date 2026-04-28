package org.example.repository.Relatorio;

import java.sql.SQLException;
import java.util.ArrayList;

import org.example.model.RelatorioIncidente;

public interface RelatorioRepository {

    ArrayList<RelatorioIncidente> relatorioIncidentes () throws SQLException;
}
