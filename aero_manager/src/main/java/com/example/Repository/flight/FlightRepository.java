package com.example.Repository.Flight;

import java.sql.SQLException;

import com.example.Model.Flight;

public interface FlightRepository {

    Flight save(Flight flight) throws SQLException;

    Flight findById(int chosenId) throws SQLException;

    void update(Flight flight) throws SQLException;

    void delete(int chosenId) throws SQLException;
}
