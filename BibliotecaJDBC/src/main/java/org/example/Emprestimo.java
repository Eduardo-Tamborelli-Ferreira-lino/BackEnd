package org.example;

public class Emprestimo {
    private int id;
    private int livroId;
    private String dataEmprestimo;
    private String nomeCliente;

    public Emprestimo(int id, int livroId, String dataEmprestimo, String nomeCliente) {
        this.id = id;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.nomeCliente = nomeCliente;
    }

    public Emprestimo(int livroId, String dataEmprestimo, String nomeCliente) {
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.nomeCliente = nomeCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nLivro ID: " + getLivroId() + "\nData Emprestimo: " + getDataEmprestimo() + "\nNome Cliente: " + getNomeCliente() +
                "\n----------------------------------------------";
    }
}
