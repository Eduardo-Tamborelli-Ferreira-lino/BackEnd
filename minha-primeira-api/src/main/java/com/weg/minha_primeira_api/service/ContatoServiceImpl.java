package com.weg.minha_primeira_api.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.minha_primeira_api.dto.ContatoRequisicaoDto;
import com.weg.minha_primeira_api.dto.ContatoRespostaDto;
import com.weg.minha_primeira_api.mapper.ContatoMapper;
import com.weg.minha_primeira_api.model.Contato;
import com.weg.minha_primeira_api.repository.ContatoRepository;

@Service
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;

    public ContatoServiceImpl(ContatoRepository contatoRepository, ContatoMapper contatoMapper) {
        this.contatoRepository = contatoRepository;
        this.contatoMapper = contatoMapper;
    }

    @Override
    public ContatoRespostaDto post(ContatoRequisicaoDto contatoRequisicaoDto) throws SQLException {
        
        Contato contato = contatoMapper.paraEntidade(contatoRequisicaoDto);
        Contato contatoCriado = contatoRepository.post(contato);
        ContatoRespostaDto contatoRespostaDto = contatoMapper.paraRespostaDto(contatoCriado);

        return contatoRespostaDto;
    }

    @Override
    public ContatoRespostaDto get(Long id) throws SQLException {

        Contato contato = contatoRepository.get(id);

        return contatoMapper.paraRespostaDto(contato);
    }

    @Override
    public ContatoRespostaDto put(Long id, ContatoRequisicaoDto requisicaoDto) throws SQLException {

        Contato contato = contatoMapper.paraEntidade(requisicaoDto);
        contato.setId(id);
        Contato contatoAtualizado = contatoRepository.put(contato);
        
        return contatoMapper.paraRespostaDto(contatoAtualizado);
    }

    @Override
    public void delete(Long id) throws SQLException {

        contatoRepository.delete(id);
    }

    @Override
    public List<ContatoRespostaDto> getAll() throws SQLException {

        List <Contato> contatos = contatoRepository.getAll();

        if (contatos == null || contatos.isEmpty()) {
            throw new RuntimeException("Lista está vazia");
        }

        return contatos.stream().map(contatoMapper :: paraRespostaDto).toList();
    }

}
