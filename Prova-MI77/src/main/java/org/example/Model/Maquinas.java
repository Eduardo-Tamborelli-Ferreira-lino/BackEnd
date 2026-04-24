package org.example.Model;

public class Maquinas {
    private int id;
    private String cadastro;
    private String setor;
    private String status;

    public Maquinas(int id, String cadastro, String setor, String status) {
        this.id = id;
        this.cadastro = cadastro;
        this.setor = setor;
        this.status = status;
    }

    public Maquinas(String cadastro, String setor, String status) {
        this.cadastro = cadastro;
        this.setor = setor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCadastro() {
        return cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
