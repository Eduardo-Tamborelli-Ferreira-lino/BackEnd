package org.example.Enum;

public enum statusMaquina {
    OPERACIONAL ("Operacional"),
    EM_MANUTENCAO ("Em Manutenção");

    private final String nome;

    statusMaquina(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
