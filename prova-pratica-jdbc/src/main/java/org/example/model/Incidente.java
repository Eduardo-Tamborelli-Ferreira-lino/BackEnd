package org.example.model;

import java.time.LocalDate;

public class Incidente {
    private Integer id;
    private Integer alocacao_id;
    private String descricao;
    private String gravidade;
    private LocalDate data_incidente;

    public Incidente() {}

    public Incidente(Integer alocacao_id, String descricao, String gravidade, LocalDate data_incidente) {
        this.alocacao_id = alocacao_id;
        this.descricao = descricao;
        this.gravidade = gravidade;
        this.data_incidente = data_incidente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlocacao_id() {
        return alocacao_id;
    }

    public void setAlocacao_id(Integer alocacao_id) {
        this.alocacao_id = alocacao_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public LocalDate getData_incidente() {
        return data_incidente;
    }

    public void setData_incidente(LocalDate data_incidente) {
        this.data_incidente = data_incidente;
    }
}
