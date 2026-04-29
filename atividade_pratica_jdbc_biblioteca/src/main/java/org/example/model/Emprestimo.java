package org.example.model;

import java.time.LocalDate;

public class Emprestimo {
    private Integer id;
    private Integer leitor_id;
    private Integer livro_id;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;

    public Emprestimo() {
    }

    public Emprestimo(Integer id, Integer leitor_id, Integer livro_id, LocalDate data_emprestimo, LocalDate data_devolucao) {
        this.id = id;
        this.leitor_id = leitor_id;
        this.livro_id = livro_id;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeitor_id() {
        return leitor_id;
    }

    public void setLeitor_id(Integer leitor_id) {
        this.leitor_id = leitor_id;
    }

    public Integer getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(Integer livro_id) {
        this.livro_id = livro_id;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
