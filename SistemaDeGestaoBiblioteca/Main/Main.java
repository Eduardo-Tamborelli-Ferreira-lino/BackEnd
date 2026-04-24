package org.example.Main;


import org.example.DAO.AutorDAO;
import org.example.DAO.LivroDAO;
import org.example.Model.Autor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Criação De Variaveis estaticas para facilitar o uso durante o codigo

    static final Scanner SC = new Scanner(System.in);
    static final LivroDAO LIVRO_DAO = new LivroDAO();
    static final AutorDAO AUTOR_DAO = new AutorDAO();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal () {
        do {
            System.out.println("""
                    Olá Faça A Escolha Do Que Deseja Fazer No Sistema.
                    
                    1 - Adicionar Autor
                    2 - Buscar Autor Pelo Seu ID
                    3 - Listar Todos Os Autores
                    4 - Deletar O Autor
                    5 - Adicionar Um Livro
                    6 - Atualizar Livro
                    7 - Buscar Livro Pelo ID
                    8 - Deletar Livro
                    9 - Listar Livros Com Autor
                    
                    0 - Sair Do Sistema
                    """);
            int opcao = SC.nextInt();
            SC.nextLine();
            switch (opcao){
                case 1:{
                    adicionarAutor();
                    break;
                }
                case 2:{
                    BuscarAutor();
                    break;
                }
                case 3:{
                    listarAutor();
                    break;
                }
                case 4:{
                    deletarAutor();
                    break;
                }
                case 5:{
                    adicionarLivro();
                    break;
                }
                case 6:{
                    atualizarLivro();
                    break;
                }
                case 7:{
                    buscarLivro();
                    break;
                }
                case 8:{
                    deletarLivro();
                    break;
                }
                case 9:{
                    listarLivrosComAutor();
                    break;
                }
                case 0:{
                    System.out.println("Encerrando O Sistema Muito Obrigado Pelo Seu Tempo. <3");
                    System.exit(0);
                }
            }
        } while(true);
    }

    public static void adicionarAutor () {
        System.out.println("Insira o nome do autor: ");
        String nome = SC.nextLine();
        if (!verificarNomeAutor(nome)){
            return;
        }
        System.out.println("Insira a nacionalidade do autor: ");
        String nacionalidade = SC.nextLine();
        Autor autor = new Autor(nome,nacionalidade);
        try {
            if (AUTOR_DAO.verificarCadastro(autor)){
                AUTOR_DAO.cadastrarAutor(autor);
                System.out.println("Autor Cadastradado ao sistema. ");
            }
            else {
                System.out.println("Nome do usuario já existe por favor insira um nome não inserido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
    }

    public static void BuscarAutor () {
        Autor autor = null;
        System.out.println("Informe o ID do Autor que deseja buscar. ");
        int idEscolha = SC.nextInt();
        if (verificarExistenciaAutor(idEscolha)){
            try {
                autor = AUTOR_DAO.buscarPorId(idEscolha);
            }
            catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados ");
                e.printStackTrace();
            }
            if (autor == null){
                System.out.println("Nenhum Autor Insirido, Por favor Insira um autor");
                return;
            }
            System.out.println("ID: " + autor.getId() +
                    "Nome: " + autor.getNome() +
                    "Nacionalidade: " + autor.getNacionalidade());
        }
    }

    public static void listarAutor () {

    }

    public static void deletarAutor () {

    }

    public static void adicionarLivro () {

    }

    public static void atualizarLivro () {

    }

    public static void buscarLivro () {

    }

    public static void deletarLivro () {

    }

    public static void listarLivrosComAutor () {

    }

    public static void listarLivro () {}

    public static boolean verificarNomeAutor (String nome) {
        if (nome != null) {
            System.out.println("Nome inserido é valido continue a inserção de dado. ");
            return true;
        }
        System.out.println("Informe um nome de forma valida. ");
        return false;
    }

    public static boolean verificarTituloLivro () {

    }

    public static boolean verificarExistenciaAutor (int idEscolha) {
        ArrayList <Autor> autores = null;
        try {
            autores = AUTOR_DAO.listarAutores();
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
        try {
            for (Autor autor : autores){
                if (autor.getId() == idEscolha){
                    System.out.println("ID Escolhido é valido");
                    return true;
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Nenhum ID encontrado, Insira o ID primeiro ");
            e.printStackTrace();
        }
    }

    public static boolean verificarExistenciaLivro () {

    }

    public static void atualizarTituloLivro () {}

    public static void atualizarAnoLivro () {}

    public static void atualizarAutorId () {}

}