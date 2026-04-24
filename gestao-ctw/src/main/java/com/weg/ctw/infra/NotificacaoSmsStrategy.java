package com.weg.ctw.infra;

import com.weg.ctw.domain.INotificacaoStrategy;

public class NotificacaoSmsStrategy implements INotificacaoStrategy {

    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("    📱 [SMS] Para: " + destinatario);
        System.out.println("      Mensagem: " + mensagem);
        System.out.println("      Status: SMS enviado com sucesso!");
    }
}
