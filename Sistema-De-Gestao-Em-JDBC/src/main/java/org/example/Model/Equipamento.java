package org.example.Model;

public class Equipamento {
    private int id;
    private String nome;
    private String numeroSerie;
    private int idFornecedor;

    public Equipamento(int id, String nome, String numeroSerie, int idFornecedor) {
        this.id = id;
        this.nome = nome;
        this.numeroSerie = numeroSerie;
        this.idFornecedor = idFornecedor;
    }

    public Equipamento(String nome, String numeroSerie, int idFornecedor) {
        this.nome = nome;
        this.numeroSerie = numeroSerie;
        this.idFornecedor = idFornecedor;
    }

    public Equipamento(int id, String nome, String numeroSerie) {
        this.id = id;
        this.nome = nome;
        this.numeroSerie = numeroSerie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
}
