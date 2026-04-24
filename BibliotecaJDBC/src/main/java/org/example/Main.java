package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }

    public static void inicio(){
        System.out.println("Seja Bem-Vindo a Nossa Biblioteca.");
        while (true) {
            System.out.println("""
                    O que Deseja Fazer?
                    1 - Cadastrar Livro
                    2 - Realizar Emprestimo
                    3 - Listar 
                    4 - Excluir
                    5 - Atualizar
                    0 - Sair
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    cadastrarLivro();
                    break;
                }
                case 2:{
                    realizarEmprestimo();
                    break;
                }
                case 3:{
                    listar();
                    break;
                }
                case 4:{
                    excluir();
                    break;
                }
                case 5:{
                    atualizar();
                    break;
                }
                case 0:{
                    System.exit(0);
                }
            }
        }
    }

    public static void cadastrarLivro(){
        System.out.println("Insira o Título do livro que deseja cadastrar: ");
        SC.nextLine();
        String titulo = SC.nextLine();
        System.out.println("Insira a quantidade disponivel: ");
        int qtd = SC.nextInt();
        var dao = new LivroDao();
        try {
            dao.cadastrarLivro(titulo, qtd);
            System.out.println("Cadastro realizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
    }

    public static void realizarEmprestimo(){
        ArrayList<Livro> livros = new ArrayList<>();
        var dao = new LivroDao();
        try {
            livros = dao.listarLivros();
            if (livros == null || livros.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Livro livro : livros){
                    System.out.println(livro.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
        System.out.println("Informe o id do livro que deseja realizar o emprestimo:");
        int id = SC.nextInt();
        System.out.println("Informe o nome do cliente que está realizando o emprestimo:");
        SC.nextLine();
        String nome = SC.nextLine();
        var daoLivro = new LivroDao();
        try {
            daoLivro.realizarEmprestimo(id,nome);
            System.out.println("Emprestimo realizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
    }

    public static void listar(){
        System.out.println("""
                Você deseja:
                1 - Listar Livros
                2 - Listar Emprestimos
                3 - Listar Todos
                0 - Retornar
                """);
        int opcao = SC.nextInt();
        switch (opcao){
            case 1:{
                listarLivro();
                break;
            }
            case 2:{
                listarEmprestimo();
                break;
            }
            case 3:{
                listarTodos();
                break;
            }
            case 0:{
                return;
            }
            default:{
                System.out.println("Insira uma opção valida");
                listar();
                break;
            }
        }
    }

    public static void listarLivro() {
        ArrayList<Livro> livros = new ArrayList<>();
        var dao = new LivroDao();
        try {
            livros = dao.listarLivros();
            if (livros == null || livros.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Livro livro : livros){
                    System.out.println(livro.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
    }

    public static void listarEmprestimo(){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        var dao = new EmprestimoDao();
        try {
            emprestimos = dao.listarEmprestimos();
            if (emprestimos == null || emprestimos.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Emprestimo emprestimo : emprestimos){
                    System.out.println(emprestimo.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
    }

    public static void listarTodos(){
        ArrayList<Livro> livros = new ArrayList<>();
        var dao = new LivroDao();
        try {
            livros = dao.listarLivros();
            if (livros == null || livros.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Livro livro : livros){
                    System.out.println(livro.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        var daoEmprestimo = new EmprestimoDao();
        try {
            emprestimos = daoEmprestimo.listarEmprestimos();
            if (emprestimos == null || emprestimos.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Emprestimo emprestimo : emprestimos){
                    System.out.println(emprestimo.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
    }

    public static void excluir(){
        System.out.println("""
                Você deseja excluir:
                1 - Livro
                2 - Emprestimo 
                0 - Retornar
                """);
        int opcao = SC.nextInt();
        switch (opcao){
            case 1:{
                excluirLivro();
                break;
            }
            case 2:{
                excluirEmprestimo();
                break;
            }
            case 0:{
                return;
            }
            default:{
                System.out.println("Escolha uma opção valida");
                excluir();
                break;
            }
        }
    }

    public static void excluirLivro(){
        ArrayList<Livro> livros = new ArrayList<>();
        var dao = new LivroDao();
        try {
            livros = dao.listarLivros();
            if (livros == null || livros.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Livro livro : livros){
                    System.out.println(livro.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
        System.out.println("Informe o ID do livro que deseja excluir: ");
        int id = SC.nextInt();
        var daoLivro = new LivroDao();
        try {
            livros = dao.listarLivros();
            if (livros == null || livros.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Livro livro : livros){
                    if (livro.getId() == id){
                        System.out.println(livro.toString());
                    }
                }
            }
            System.out.println("""
            Você tem certeza que deseja excluir esse livro?
            1 - Sim 
            2 - Não
            """);
            int opcao = SC.nextInt();
            if (opcao == 1){
                System.out.println("""
                        Insira a palavra chave: 
                        Eichendorf
                        """);
                SC.nextLine();
                String palavraChave = SC.nextLine();
                if (palavraChave.equals("Eichendorf")){
                    daoLivro.excluirLivro(id);
                    System.out.println("Livro excluido com sucesso");
                }
                else{
                    System.out.println("Palavra chave inserida errado recomeçe o processo de excluir");
                    excluir();
                }
            }
            else{
                System.out.println("Como você não quer mais excluir vamos retornar ao menu principal");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
    }

    public static void excluirEmprestimo(){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        var dao = new EmprestimoDao();
        try {
            emprestimos = dao.listarEmprestimos();
            if (emprestimos == null || emprestimos.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Emprestimo emprestimo : emprestimos){
                    System.out.println(emprestimo.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
        System.out.println("Insira o ID do emprestimo que deseja excluir: ");
        int id = SC.nextInt();
        var daoEmprestimo = new EmprestimoDao();
        try {
            emprestimos = dao.listarEmprestimos();
            if (emprestimos == null || emprestimos.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Emprestimo emprestimo : emprestimos){
                    if (emprestimo.getId() == id){
                        System.out.println(emprestimo.toString());
                    }
                }
            }
            System.out.println("""
                    Tem certeza que deseja excluir esse emprestimo
                    1 - Sim
                    2 - Não
                    """);
            int opcao = SC.nextInt();
            if (opcao == 1){
                System.out.println("""
                        Insira a palavra chave: 
                        Eichendorf
                        """);
                SC.nextLine();
                String palavraChave = SC.nextLine();
                if (palavraChave.equals("Eichendorf")){
                    daoEmprestimo.excluirEmprestimo(id);
                    System.out.println("Livro excluido com sucesso");
                }
                else{
                    System.out.println("Palavra chave inserida errado recomeçe o processo de excluir");
                    excluir();
                }
            }
            else{
                System.out.println("Como você não quer mais excluir vamos retornar ao menu principal");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
    }

    public static void atualizar(){
        System.out.println("""
                O que Você Atualizar:
                1 - Livro 
                2 - Emprestimo 
                0 - Retornar
                """);
        int opcao = SC.nextInt();
        switch (opcao){
            case 1:{
                atualizarLivro();
                break;
            }
            case 2:{
                atualizarEmprestimo();
                break;
            }
            case 0:{
                return;
            }
            default:{
                System.out.println("Insira uma opção valida.");

                atualizar();
            }
        }
    }

    public static void atualizarLivro(){
        System.out.println("""
                O que você deseja atualizar? 
                1 - Titulo.
                2 - Quantidade no Estoque.
                0 - Retornar
                """);
        int opcao = SC.nextInt();
        switch (opcao){
            case 1:{
                atualizarLivroTitulo();
                break;
            }
            case 2:{
                atualizarLivroQuantidade();
                break;
            }
            case 0:{
                return;
            }
            default:{
                System.out.println("Insira uma opção valida.");
                atualizarLivro();
            }
        }
    }

    public static void atualizarLivroTitulo(){
        ArrayList<Livro> livros = new ArrayList<>();
        var dao = new LivroDao();
        try {
            livros = dao.listarLivros();
            if (livros == null || livros.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Livro livro : livros){
                    System.out.println(livro.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
        System.out.println("Informe o ID do livro que deseja alterar o titulo:");
        int id = SC.nextInt();
        for (Livro livro : livros){
            if (livro.getId() == id){
                System.out.println("informe o novo titulo do livro");
                SC.nextLine();
                String titulo = SC.nextLine();
                var daoLivro = new LivroDao();
                try {
                    daoLivro.atualizarLivroTitulo(titulo,id);
                    System.out.println("Titulo Atualizado com Sucesso");
                    return;
                } catch (SQLException e) {
                    System.out.println("Erro no banco de dados tente novamente.");
                    e.printStackTrace();
                }
            }
        }
        System.out.println("ID não encontrado tente novamente");
        atualizarLivroTitulo();
    }

    public static void atualizarLivroQuantidade(){
        ArrayList<Livro> livros = new ArrayList<>();
        var dao = new LivroDao();
        try {
            livros = dao.listarLivros();
            if (livros == null || livros.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Livro livro : livros){
                    System.out.println(livro.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
        System.out.println("Informe o ID do livro que deseja alterar a quantidade:");
        int id = SC.nextInt();
        for (Livro livro : livros){
            if (livro.getId() == id){
                System.out.println("informe o novo quantidade do livro");
                int quantidade = SC.nextInt();
                var daoLivro = new LivroDao();
                try {
                    daoLivro.atualizarLivroQuantidade(quantidade,id);
                    System.out.println("Quantidade Atualizado com Sucesso");
                    return;
                } catch (SQLException e) {
                    System.out.println("Erro no banco de dados tente novamente.");
                    e.printStackTrace();
                }
            }
        }
        System.out.println("ID não encontrado tente novamente");
        atualizarLivroQuantidade();
    }

    public static void atualizarEmprestimo(){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        var dao = new EmprestimoDao();
        try {
            emprestimos = dao.listarEmprestimos();
            if (emprestimos == null || emprestimos.isEmpty()){
                System.out.println("Nenhum livro cadastrado até o momento");
            }
            else {
                for (Emprestimo emprestimo : emprestimos){
                    System.out.println(emprestimo.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados tente novamente.");
            e.printStackTrace();
        }
        System.out.println("Informe o ID do emprestimo que deseja alterar o nome do cliente:");
        int id = SC.nextInt();
        for (Emprestimo emprestimo: emprestimos){
            if (emprestimo.getId() == id ){
                System.out.println("Informe novo nome do cliente:");
                SC.nextLine();
                String nome = SC.nextLine();
                var daoEmprestimo = new EmprestimoDao();
                try {
                    daoEmprestimo.atualizarEmprestino(nome,id);
                    System.out.println("Emprestimo atualizado com Sucesso!!");
                    return;
                }catch (SQLException e){
                    System.out.println("Erro no banco de dados tente novamente.");
                    e.printStackTrace();
                }
            }
        }
        System.out.println("ID não encontrado tente novamente");
        atualizarEmprestimo();
    }
}