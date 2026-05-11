package com.weg.minha_primeira_api.mapper;

import org.springframework.stereotype.Component;

import com.weg.minha_primeira_api.dto.ContatoRequisicaoDto;
import com.weg.minha_primeira_api.dto.ContatoRespostaDto;
import com.weg.minha_primeira_api.model.Contato;

@Component
public class ContatoMapper {
    public Contato paraEntidade(ContatoRequisicaoDto requisicaoDto) {
        return new Contato(
            requisicaoDto.nome(),
            requisicaoDto.numero()
        );
    }

    public ContatoRespostaDto paraRespostaDto (Contato contato) {
        return new ContatoRespostaDto(
            contato.getId(),
            contato.getNome(),
            contato.getNumero()
        );
    }
}
