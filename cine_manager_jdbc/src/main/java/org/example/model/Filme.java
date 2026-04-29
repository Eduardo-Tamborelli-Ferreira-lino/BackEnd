package org.example.model;

public class Filme {
    private Integer id;
    private String titulo;
    private String genero;
    private String status;

    public Filme() {}

    public Filme(Integer id, String titulo, String genero, String status) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.status = status;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
