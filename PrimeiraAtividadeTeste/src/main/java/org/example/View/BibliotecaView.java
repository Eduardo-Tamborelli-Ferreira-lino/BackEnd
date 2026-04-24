package org.example.View;

import java.util.Scanner;

public class BibliotecaView {

    static final Scanner SC = new Scanner(System.in);

    public void mostrarMenu () {
        System.out.println("""
                1 - Cadastrar Livro
                2 - Consultar Livros
                3 - Registrar Emprestimo
                4 - Registrar Devolução
                """);
        int opcao = capturarOpcao();
        switch (opcao){
            case 1:{

            }
            case 2:{

            }
            case 3:{

            }
            case 4:{

            }
        }
    }

    public int capturarOpcao () {
        int opcao = 0;
        try {
             opcao = SC.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opcao;
    }

}