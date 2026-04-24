package com.weg.ctw.infra;

import com.weg.ctw.domain.INotificacaoStrategy;

public class NotificacaoEmailStrategy implements INotificacaoStrategy {

    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("    ✉ [EMAIL] Para: " + destinatario);
        System.out.println("      Mensagem: " + mensagem);
        System.out.println("      Status: Email enviado com sucesso!");
    }
}
