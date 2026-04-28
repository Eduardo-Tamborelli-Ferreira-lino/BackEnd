package org.example.model;

public class RelatorioIncidente {
    private String nomeMotorista;
    private String cnh;
    private String placaVeiculo;
    private String descricaoIncidente;
    private String gravidade;

    public RelatorioIncidente() {}

    public RelatorioIncidente(String nomeMotorista, String cnh, String placaVeiculo, String descricaoIncidente, String gravidade) {
        this.nomeMotorista = nomeMotorista;
        this.cnh = cnh;
        this.placaVeiculo = placaVeiculo;
        this.descricaoIncidente = descricaoIncidente;
        this.gravidade = gravidade;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getDescricaoIncidente() {
        return descricaoIncidente;
    }

    public void setDescricaoIncidente(String descricaoIncidente) {
        this.descricaoIncidente = descricaoIncidente;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }
}
