package com.weg.ctw.domain;

public interface INotificacaoStrategy {

    void enviar(String destinatario, String mensagem);
}
