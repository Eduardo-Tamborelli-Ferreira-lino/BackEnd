package com.weg.minha_primeira_api.repository;

import java.sql.SQLException;
import java.util.ArrayList;

import com.weg.minha_primeira_api.model.Contato;

public interface ContatoRepository {

    Contato post (Contato contato) throws SQLException;

    Contato get (Long id) throws SQLException;

    ArrayList<Contato> getAll () throws SQLException;

    void put (Contato contato) throws SQLException;

    void delete (Long id) throws SQLException;

}
