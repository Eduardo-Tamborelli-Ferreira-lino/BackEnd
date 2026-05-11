package com.weg.minha_primeira_api.service;

import java.sql.SQLException;
import java.util.List;

import com.weg.minha_primeira_api.dto.ContatoRequisicaoDto;
import com.weg.minha_primeira_api.dto.ContatoRespostaDto;

public interface ContatoService {

    ContatoRespostaDto post (ContatoRequisicaoDto contatoRequisicaoDto) throws SQLException;

    ContatoRespostaDto get (Long id) throws SQLException;

    List<ContatoRespostaDto> getAll () throws SQLException;

    ContatoRespostaDto put (Long id, ContatoRequisicaoDto requisicaoDto) throws SQLException;

    void delete (Long id) throws SQLException;
}
