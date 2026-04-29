package org.example.model;

public class Infracao {
    private Integer id;
    private Integer emprestimo_id;
    private String descricao;
    private String gravidade;

    public Infracao() {
    }

    public Infracao(Integer id, Integer emprestimo_id, String descricao, String gravidade) {
        this.id = id;
        this.emprestimo_id = emprestimo_id;
        this.descricao = descricao;
        this.gravidade = gravidade;
    }

    public Infracao(Integer id, String descricao, String gravidade) {
        this.id = id;
        this.descricao = descricao;
        this.gravidade = gravidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmprestimo_id() {
        return emprestimo_id;
    }

    public void setEmprestimo_id(Integer emprestimo_id) {
        this.emprestimo_id = emprestimo_id;
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
}
