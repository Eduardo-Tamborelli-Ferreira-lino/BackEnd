package org.example.repository.Incidente;

import java.sql.SQLException;

import org.example.model.Incidente;

public interface IncidenteRepository {

    Incidente save(Incidente incidente) throws SQLException;
    
}
