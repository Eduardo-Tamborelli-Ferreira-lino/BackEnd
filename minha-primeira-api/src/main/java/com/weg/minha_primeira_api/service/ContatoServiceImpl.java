package com.weg.minha_primeira_api.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.weg.minha_primeira_api.model.Contato;
import com.weg.minha_primeira_api.repository.ContatoRepository;

@Service
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoServiceImpl(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Override
    public Contato post(Contato contato) throws SQLException {
        contatoRepository.post(contato);

        return contato;
    }

    @Override
    public Contato get(Long id) throws SQLException {

        Contato contato = contatoRepository.get(id);

        return contato;
    }

    @Override
    public void put(Contato contato) throws SQLException {

        contatoRepository.put(contato);
    }

    @Override
    public void delete(Long id) throws SQLException {

        contatoRepository.delete(id);
    }

    @Override
    public ArrayList<Contato> getAll() throws SQLException {

        ArrayList <Contato> contatos = contatoRepository.getAll();

        if (contatos == null || contatos.isEmpty()) {
            throw new RuntimeException("Lista está vazia");
        }

        return contatos;
    }

}
