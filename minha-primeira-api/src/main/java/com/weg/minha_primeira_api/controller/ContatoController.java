package com.weg.minha_primeira_api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.minha_primeira_api.dto.ContatoRequisicaoDto;
import com.weg.minha_primeira_api.dto.ContatoRespostaDto;
import com.weg.minha_primeira_api.service.ContatoService;

// Conhecido como end point
// Função: recebe requisição

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public ContatoRespostaDto post(@RequestBody ContatoRequisicaoDto contatoRequisicaoDto) {
        try {
            return contatoService.post(contatoRequisicaoDto);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ContatoRespostaDto get(@PathVariable Long id) {
        try {
            return contatoService.get(id);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<ContatoRespostaDto> getAll() {
        try {
            return contatoService.getAll();
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ContatoRespostaDto put(@PathVariable Long id, @RequestBody ContatoRequisicaoDto contatoRequisicaoDto) {
        try {
            return contatoService.put(id, contatoRequisicaoDto);
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try{
            contatoService.delete(id);
        }catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
