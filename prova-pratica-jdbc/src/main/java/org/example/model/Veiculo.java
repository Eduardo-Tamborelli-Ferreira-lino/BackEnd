package org.example.model;

public class Veiculo {
    private Integer id;
    private String placa;
    private String modelo;
    private String status;

    public Veiculo() {}

    public Veiculo(String placa, String modelo, String status) {
        this.placa = placa;
        this.modelo = modelo;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
