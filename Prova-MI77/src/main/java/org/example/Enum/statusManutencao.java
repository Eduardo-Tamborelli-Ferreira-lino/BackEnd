package org.example.Enum;

public enum statusManutencao {
    PENDENTE ("Pendente"),
    EXECUTADA ("Executada"),
    CANCELADA ("Cancelada");

    private final String nome;

    statusManutencao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
