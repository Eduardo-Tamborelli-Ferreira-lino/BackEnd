package com.weg.ctw.domain;

public class ProfessorSenai extends Professor {

    public ProfessorSenai(int id, String nome) {
        super(id, nome);
    }

    @Override
    public double getCargaHorariaMaxima() {
        return 40.0;
    }

    @Override
    public String getTipo() {
        return "SENAI";
    }
}
