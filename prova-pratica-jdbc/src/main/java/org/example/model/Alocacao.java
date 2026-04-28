package org.example.model;

import java.time.LocalDate;

public class Alocacao {
    private Integer id;
    private Integer motorista_id;
    private Integer veiculo_id;
    private LocalDate data_inicio;
    private LocalDate data_fim;

    public Alocacao() {}

    

    public Alocacao(Integer id, Integer motorista_id, Integer veiculo_id, LocalDate data_inicio, LocalDate data_fim) {
        this.id = id;
        this.motorista_id = motorista_id;
        this.veiculo_id = veiculo_id;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }



    public Alocacao(Integer motorista_id, Integer veiculo_id, LocalDate data_inicio, LocalDate data_fim) {
        this.motorista_id = motorista_id;
        this.veiculo_id = veiculo_id;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(Integer motorista_id) {
        this.motorista_id = motorista_id;
    }

    public Integer getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Integer veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }
}
