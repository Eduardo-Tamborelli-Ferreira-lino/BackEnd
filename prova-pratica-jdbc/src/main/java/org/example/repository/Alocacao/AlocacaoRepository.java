package org.example.repository.Alocacao;

import java.sql.SQLException;

import org.example.model.Alocacao;
import org.example.model.Incidente;

public interface AlocacaoRepository {

    Integer buscarIdVeiculo(Incidente incidente) throws SQLException;

    Alocacao buscarPorId(Integer id) throws SQLException;

    Alocacao atualizarMotorista(Alocacao alocacao) throws SQLException;

    Alocacao atualizarDataFim(Alocacao alocacao) throws SQLException;

    Boolean buscarAlocacaoMotorista(Integer id) throws SQLException;
}
