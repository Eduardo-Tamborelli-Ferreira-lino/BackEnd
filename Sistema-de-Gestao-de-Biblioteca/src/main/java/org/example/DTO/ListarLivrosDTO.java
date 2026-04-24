package org.example.DTO;

public class ListarLivrosDTO {
    private int idAutor;
    private String nomeAutor;
    private int idLivro;
    private String tituloLivro;
    private int anoPublicacao;

    public ListarLivrosDTO(int idAutor, String nomeAutor, int idLivro, String tituloLivro, int anoPublicacao) {
        this.idAutor = idAutor;
        this.nomeAutor = nomeAutor;
        this.idLivro = idLivro;
        this.tituloLivro = tituloLivro;
        this.anoPublicacao = anoPublicacao;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
}
