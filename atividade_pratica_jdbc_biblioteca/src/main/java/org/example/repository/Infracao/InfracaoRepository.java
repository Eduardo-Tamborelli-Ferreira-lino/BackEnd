package org.example.repository.Infracao;

import java.sql.SQLException;

import org.example.model.Infracao;

public interface InfracaoRepository {

    Infracao save (Infracao infracao) throws SQLException;
}
