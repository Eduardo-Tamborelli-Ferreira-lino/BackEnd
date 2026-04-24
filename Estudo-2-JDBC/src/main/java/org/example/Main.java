package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        while(true){
            inicio();
        }
    }

    public static void inicio(){
        while (true){
            System.out.println("""
                Olá bem-vindo à nossa Lojinha.
                O que deseja fazer?
                1 - Cadastrar Produto.
                2 - Listar Estoque.
                3 - Atualizar Preço.
                4 - Excluir Produto.
                5 - Relatório de Baixo Estoque.
                0 - Sair.
                """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:{
                    cadastrarProduto();
                    break;
                }
                case 2:{
                    listarEstoque();
                    break;
                }
                case 3:{
                    atualizarPreco();
                    break;
                }
                case 4:{
                    excluirProduto();
                    break;
                }
                case 5:{
                    baixoEstoque();
                    break;
                }
                case 0:{
                    return;
                }
                default:{
                    System.out.println("Informe uma opção valida.");
                }
            }
        }
    }

    public static void cadastrarProduto(){
        System.out.println("Vamos começar o cadastro");
        System.out.println("Insira o Nome do Produto: ");
        SC.nextLine();
        String nome = SC.nextLine();
        System.out.println("Insira o Preço do Produto: ");
        Double preco = SC.nextDouble();
        System.out.println("Insira a Quantidade de Produto: ");
        int qtd = SC.nextInt();
        var dao = new ProdutoDao();
        try {
            dao.cadastrarProduto(nome,preco,qtd);
            System.out.println("Cadastro Feito com Sucesso");
            return;
        }catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
    }

    public static void listarEstoque(){
        ArrayList <Produto> produtos = new ArrayList<>();
        var dao = new ProdutoDao();
        try{
            produtos = dao.listarProdutos();
            if (produtos == null ||produtos.isEmpty()){
                System.out.println("Nenhum Produto foi Cadastrado até o Momento.");
                return;
            }
            else{
                for (Produto produto : produtos){
                    System.out.println(produto.toString());
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
    }

    public static void atualizarPreco(){
        ArrayList <Produto> produtos = new ArrayList<>();
        var dao = new ProdutoDao();
        try{
            produtos = dao.listarProdutos();
            if (produtos == null ||produtos.isEmpty()){
                System.out.println("Nenhum Produto foi Cadastrado até o Momento.");
                return;
            }
            else{
                for (Produto produto : produtos){
                    System.out.println(produto.toString());
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
        System.out.println("""
                Por favor informe o ID do produto que deseja atualizar o preço:
                """);
        int id = SC.nextInt();
        System.out.println("""
                Por favor insira o novo preço deste produto:
                """);
        Double preco = SC.nextDouble();
        var precoDao = new ProdutoDao();
        try{
            precoDao.atualizarPreco(preco,id);
            System.out.println("O preço do produto foi atualizado");
            return;
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
    }

    public static void excluirProduto(){
        listarEstoque();
        System.out.println("Insira o ID do produto que deseja inserir: ");
        int id = SC.nextInt();
        var dao = new ProdutoDao();
        try {
            dao.excluirProduto(id);
            System.out.println("O seu produto foi excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
    }

    public static void baixoEstoque(){
        ArrayList <Produto> produtos = new ArrayList<>();
        var dao = new ProdutoDao();
        try{
            produtos = dao.listarProdutos();
            if (produtos == null ||produtos.isEmpty()){
                System.out.println("Nenhum Produto foi Cadastrado até o Momento.");
                return;
            }
            else{
                for (Produto produto : produtos){
                    if (produto.getQtd() <= 10){
                        System.out.println(produto.toString());
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
    }
}