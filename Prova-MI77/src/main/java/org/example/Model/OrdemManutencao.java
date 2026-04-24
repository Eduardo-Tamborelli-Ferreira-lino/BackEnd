package org.example.Model;

import java.time.LocalDate;

public class OrdemManutencao {
    private int id;
    private int idMaquina;
    private int idTecnico;
    private LocalDate dataManutencao;
    private String status;

    public OrdemManutencao(int id, int idMaquina, int idTecnico, LocalDate dataManutencao, String status) {
        this.id = id;
        this.idMaquina = idMaquina;
        this.idTecnico = idTecnico;
        this.dataManutencao = dataManutencao;
        this.status = status;
    }

    public OrdemManutencao(int idMaquina, int idTecnico, LocalDate dataManutencao, String status) {
        this.idMaquina = idMaquina;
        this.idTecnico = idTecnico;
        this.dataManutencao = dataManutencao;
        this.status = status;
    }

    public OrdemManutencao(int id, LocalDate dataManutencao, String status) {
        this.id = id;
        this.dataManutencao = dataManutencao;
        this.status = status;
    }

    public OrdemManutencao(int id, int idMaquina, String status) {
        this.id = id;
        this.idMaquina = idMaquina;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public LocalDate getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(LocalDate dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
