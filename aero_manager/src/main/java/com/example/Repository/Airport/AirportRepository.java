package com.example.Repository.Airport;

import java.sql.SQLException;

import com.example.Model.Airport;

public interface AirportRepository {

    Airport sava(Airport airport) throws SQLException;

    Airport findById(long chosenId) throws SQLException;
}
