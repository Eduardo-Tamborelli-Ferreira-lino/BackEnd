package com.weg.ctw.domain;

public class ProfessorWeg extends Professor {

    public ProfessorWeg(int id, String nome) {
        super(id, nome);
    }

    @Override
    public double getCargaHorariaMaxima() {
        return 20.0;
    }

    @Override
    public String getTipo() {
        return "WEG";
    }
}
