package org.example.Main;

import org.example.DAO.AutorDAO;
import org.example.DAO.LivroDAO;
import org.example.DTO.ListarLivrosDTO;
import org.example.Model.Autor;
import org.example.Model.Livro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Criação De Variaveis estaticas para facilitar o uso durante o codigo

    static final Scanner SC = new Scanner(System.in);
    static final LivroDAO LIVRO_DAO = new LivroDAO();
    static final AutorDAO AUTOR_DAO = new AutorDAO();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
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
            switch (opcao) {
                case 1: {
                    adicionarAutor();
                    break;
                }
                case 2: {
                    buscarAutor();
                    break;
                }
                case 3: {
                    listarAutor();
                    break;
                }
                case 4: {
                    deletarAutor();
                    break;
                }
                case 5: {
                    adicionarLivro();
                    break;
                }
                case 6: {
                    atualizarLivro();
                    break;
                }
                case 7: {
                    buscarLivro();
                    break;
                }
                case 8: {
                    deletarLivro();
                    break;
                }
                case 9: {
                    listarLivrosComAutor();
                    break;
                }
                case 0: {
                    System.out.println("Encerrando O Sistema Muito Obrigado Pelo Seu Tempo. <3");
                    System.exit(0);
                }
            }
        } while (true);
    }

    public static void adicionarAutor() {
        System.out.println("Insira o nome do autor: ");
        String nome = SC.nextLine();
        if (!verificarNomeAutor(nome)) {
            return;
        }
        System.out.println("Insira a nacionalidade do autor: ");
        String nacionalidade = SC.nextLine();
        Autor autor = new Autor(nome, nacionalidade);
        try {
            if (AUTOR_DAO.verificarCadastro(autor)) {
                AUTOR_DAO.cadastrarAutor(autor);
                System.out.println("Autor Cadastradado ao sistema. ");
            } else {
                System.out.println("Nome do usuario já existe por favor insira um nome não inserido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
    }

    public static int buscarAutor() {
        System.out.println("Informe o ID do Autor que deseja buscar. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        try {
            Autor autor = AUTOR_DAO.buscarPorId(idEscolha);
            System.out.println("ID: " + autor.getId() +
                    "Nome: " + autor.getNome() +
                    "Nacionalidade: " + autor.getNacionalidade());
            return idEscolha;
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
        return 0;
    }

    public static void listarAutor() {
        ArrayList<Autor> autores = null;
        try {
            autores = AUTOR_DAO.listarAutores();
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
        if (autores == null || autores.isEmpty()) {
            System.out.println("Nenhum Autor Insirido, Por favor Insira um autor");
            return;
        }
        for (Autor autor : autores) {
            System.out.println("ID: " + autor.getId() +
                    "Nome: " + autor.getNome() +
                    "Nacionalidade: " + autor.getNacionalidade());
        }
    }

    public static void deletarAutor() {
        listarAutor();
        int idEscolhido = buscarAutor();
        System.out.println("OK. Agora Insira A Chave De Segurança: ");
        String chave = SC.nextLine();
        if (verificarChave(chave)) {
            try {
                AUTOR_DAO.deletarAutor(idEscolhido);
            } catch (RuntimeException e) {
                System.out.println("ERRO!!!! " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados ");
                e.printStackTrace();
            }
        } else {
            System.out.println("Chave De Segurança Incorreta. ");
        }
    }

    public static void adicionarLivro() {
        try {
            int autorId = buscarAutor();
            System.out.println("Insira o Titulo Do Livro: ");
            String titulo = SC.nextLine();
            if (verificarTituloLivro(titulo)) {
                System.out.println("Insira o Ano de Publicação do Livro " +
                        "Caso Não Tenha, Informe Alguem Numero Menor ou Igual a Zero");
                int ano = SC.nextInt();
                Livro livro = verificarAnoLivro(autorId, titulo, ano);
                LIVRO_DAO.cadastrarLivro(livro);
            }
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
    }

    public static void atualizarLivro() {
        while (true) {
            System.out.println("""
                    Informe o que deseja atualizar no Livro

                    1 - Titulo
                    2 - Ano de Publicação
                    3 - Id do Autor
                    4 - Tudo

                    0 - Sair

                    """);
            int opcao = SC.nextInt();
            switch (opcao) {
                case 1: {
                    atualizarTituloLivro();
                    break;
                }
                case 2: {
                    atualizarAnoLivro();
                    break;
                }
                case 3: {
                    atualizarAutorId();
                    break;
                }
                case 4: {
                    atualizarTituloLivro();
                    atualizarAnoLivro();
                    atualizarAutorId();
                    break;
                }
                case 0: {
                    System.out.println("Ok, vamos retornar ao menu inicial. ");
                    return;
                }
                default:
                    System.out.println("Por favor Insira Uma Opção Valida. ");
                    break;
            }
        }
    }

    public static void atualizarTituloLivro() {
        try {
            int idEscolhido = buscarLivro();
            System.out.println("Informe o novo Titulo do livro. ");
            String titulo = SC.nextLine();
            if (!verificarTituloLivro(titulo)) {
                return;
            }
            LIVRO_DAO.atualizarLivroTitulo(titulo, idEscolhido);
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
    }

    public static void atualizarAnoLivro() {
        try {
            int idEscolhido = buscarLivro();
            System.out.println("Informe o ano de publicação do livro. ");
            Integer ano = SC.nextInt();
            if (ano <= 0) {
                ano = null;
                LIVRO_DAO.atualizarLivroAnoPublicacao(ano, idEscolhido);
            } else {
                LIVRO_DAO.atualizarLivroAnoPublicacao(ano, idEscolhido);
            }
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
    }

    public static void atualizarAutorId() {
        try {
            int idEscolhido = buscarLivro();
            listarAutor();
            int idAutor = SC.nextInt();
            LIVRO_DAO.atualizarLivroAutor(idAutor, idEscolhido);
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
    }

    public static int buscarLivro() {
        System.out.println("Informe o ID do Livro que deseja buscar. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        try {
            Livro livro = LIVRO_DAO.buscarLivroId(idEscolha);
            System.out.println("ID: " + livro.getId() +
                    "ID do Autor: " + livro.getAutorId() +
                    "Nome: " + livro.getTitulo() +
                    "Nacionalidade: " + livro.getAnoPublicacao());
            return idEscolha;
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
        return 0;
    }

    public static void deletarLivro() {
        try {
            listarLivrosComAutor();
            int idEscolhido = buscarLivro();
            System.out.println("Por favor Insira a chave de segurança.");
            String chave = SC.nextLine();
            if (!verificarChave(chave)) {
                return;
            }
            LIVRO_DAO.deletarLivro(idEscolhido);
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }

    }

    public static void listarLivrosComAutor() {
        ArrayList<ListarLivrosDTO> livros = new ArrayList<>();
        try {
            livros = LIVRO_DAO.listarLivros();
            for (ListarLivrosDTO livro : livros) {
                System.out.println(
                        "ID do Livro: " + livro.getIdLivro() +
                                "Titulo do Livro: " + livro.getTituloLivro() +
                                "Ano de Publicação do Livro: " + livro.getAnoPublicacao() +
                                "ID do Autor: " + livro.getIdAutor() +
                                "Nome do Autor: " + livro.getNomeAutor() +
                                "---------------------------------------------------------------");
            }
        } catch (RuntimeException e) {
            System.out.println("ERRO!!!! " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados ");
            e.printStackTrace();
        }
    }

    public static boolean verificarNomeAutor(String nome) {
        if (nome != null) {
            System.out.println("Nome inserido é valido continue a inserção de dado. ");
            return true;
        }
        System.out.println("Informe um nome de forma valida. ");
        return false;
    }

    public static boolean verificarTituloLivro(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            System.out.println("Titulo Não Pode Ser Nulo ou Vazio O Cadastro Se encerrou. ");
            return false;
        }
        return true;
    }

    public static Livro verificarAnoLivro(int autorId, String titulo, Integer ano) {
        if (ano <= 0) {
            System.out.println("Ano Não é Informado. ");
            Livro livro = new Livro(autorId, titulo, null);
            return livro;
        }
        Livro livro = new Livro(autorId, titulo, ano);
        return livro;
    }

    public static boolean verificarChave(String chave) {
        if (chave.equalsIgnoreCase("ana beatriz de oliveira ribeiro")) {
            System.out.println("Chave De Segurança Insirada Corretamente. ");
            return true;
        }
        return false;
    }

}

// Go driking