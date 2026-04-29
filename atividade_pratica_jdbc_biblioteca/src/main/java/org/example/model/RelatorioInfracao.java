package org.example.model;

public class RelatorioInfracao {
    private String nomeLeitor;
    private String email;
    private String tituloLivro;
    private String descricaoInfracao;
    private String gravidade;

    public RelatorioInfracao() {
    }

    public RelatorioInfracao(String nomeLeitor, String email, String tituloLivro, String descricaoInfracao, String gravidade) {
        this.nomeLeitor = nomeLeitor;
        this.email = email;
        this.tituloLivro = tituloLivro;
        this.descricaoInfracao = descricaoInfracao;
        this.gravidade = gravidade;
    }

    public String getNomeLeitor() {
        return nomeLeitor;
    }

    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getDescricaoInfracao() {
        return descricaoInfracao;
    }

    public void setDescricaoInfracao(String descricaoInfracao) {
        this.descricaoInfracao = descricaoInfracao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }
}
