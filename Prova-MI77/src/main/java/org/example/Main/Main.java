package org.example.Main;

import org.example.Model.*;
import org.example.DAO.*;
import org.example.Enum.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static final MaquinasDao MAQUINAS_DAO = new MaquinasDao();
    static final OrdemManutencaoDao ORDEM_MANUTENCAO_DAO = new OrdemManutencaoDao();
    static final OrdemPecaDao ORDEM_PECA_DAO = new OrdemPecaDao();
    static final PecasDao PECAS_DAO = new PecasDao();
    static final TecnicosDao TECNICOS_DAO = new TecnicosDao();
    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
    }

    public static void inicio(){
        while(true){
            System.out.println("""
                    1 - Cadastrar Máquina.
                    2 - Cadastrar Técnico.
                    3 - Cadastrar Peça.
                    4 - Criar Ordem De Manutenção.
                    5 - Associar Peças à Ordem.
                    6 - Executar Manutenção.
                    
                    0 - Sair
                    """);
            int opcao = SC.nextInt();
            SC.nextLine();
            switch (opcao){
                case 1:{
                    cadastrarMaquina();
                    break;
                }
                case 2:{
                    cadastrarTecnico();
                    break;
                }
                case 3:{
                    cadastrarPeca();
                    break;
                }
                case 4:{
                    cadastrarManutencao();
                    break;
                }
                case 5:{
                    associar();
                    break;
                }
                case 6:{
                    executarManutencao();
                    break;
                }
                case 0:{
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Por favor Escolha uma opção valida");
                    break;
                }
            }
        }
    }

    public static void cadastrarMaquina() {
        System.out.println("Insira o Nome da Máquina: ");
        String cadastro = SC.nextLine();
        if (cadastro != null ){
            System.out.println("Insira o Setor Onde da Máquina: ");
            String setor = SC.nextLine();
            if (setor != null ){
                String status = statusMaquina.OPERACIONAL.getNome();
                Maquinas maquinas = new Maquinas(cadastro, setor, status);
                try {
                    if (MAQUINAS_DAO.validarEntrada(maquinas)){
                        System.out.println("Máquina já Cadastrada no Mesmo Setor.");
                    }
                    else {
                        MAQUINAS_DAO.cadastrarMaquinas(maquinas);
                        System.out.println("Máquina Cadastrada com Sucesso.");
                    }
                } catch (SQLException e) {
                    System.out.println("Erro ao Acessar o Banco de Dados");
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Você Deve Inserir um Setor.");
                cadastrarMaquina();
            }
        }
        else {
            System.out.println("Você Deve Inserir um Nome.");
            cadastrarMaquina();
        }
    }

    public static void cadastrarTecnico() {
        System.out.println("Insira o Nome do Técnico: ");
        String nome = SC.nextLine();
        if (nome != null ){
            System.out.println("Insira a Especialidade do Técnico: ");
            String especialidade = SC.nextLine();
            if (especialidade != null){
                Tecnicos tecnico = new Tecnicos(nome, especialidade);
                try {
                    if (TECNICOS_DAO.validarEntrada(tecnico)){
                        System.out.println("Técnico já Cadastrada no Sistema.");
                    }
                    else{
                        TECNICOS_DAO.cadastrarTecnico(tecnico);
                        System.out.println("Técnico foi Cadastrada com Sucesso.");
                    }
                }catch (SQLException e) {
                    System.out.println("Erro ao Acessar o Banco de Dados");
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Você Deve Inserir uma Especialidade.");
                cadastrarTecnico();
            }
        }
        else {
            System.out.println("Você Deve Inserir um Nome.");
            cadastrarTecnico();
        }
    }

    public static void cadastrarPeca() {
        System.out.println("Insira o Nome da Peça: ");
        String nome = SC.nextLine();
        if (nome != null ){
            System.out.println("Insira a Quantidade de Peça no Estoque");
            Double estoque = SC.nextDouble();
            Pecas pecas = new Pecas(nome, estoque);
            try {
                if (PECAS_DAO.validarEntrada(pecas)){
                    System.out.println("Peça já Cadastrada");
                }
                else {
                    PECAS_DAO.cadastrarPecas(pecas);
                    System.out.println("Peça Cadastrada");
                }
            }catch (SQLException e) {
                System.out.println("Erro ao Acessar o Banco de Dados");
                e.printStackTrace();
            }
        }
    }

    public static void cadastrarManutencao() {
        ArrayList<Maquinas> maquinas = new ArrayList<>();
        ArrayList<Tecnicos> tecnicos = new ArrayList<>();
        try {
            maquinas = MAQUINAS_DAO.listarMaquinas();
            if (maquinas == null || maquinas.isEmpty()){
                System.out.println("Primeiro Faça a Inserção de Alguma Máquina");
                return;
            }
            else{
                for (Maquinas maquina : maquinas){
                    System.out.println("Id: " + maquina.getId() +
                            "\nNome: " + maquina.getCadastro() +
                            "\nSetor: " + maquina.getSetor() +
                            "\nStatus: " + maquina.getStatus() +
                            "\n--------------------------------------");
                }
                System.out.println("Escolha uma das Máquinas Aprensentadas Pelo seu Respectivo ID.");
                int opcao = SC.nextInt();
                for (Maquinas maquina : maquinas){
                    if (maquina.getId() == opcao){
                        System.out.println("Escolha valida");
                        tecnicos = TECNICOS_DAO.listarTecnicos();
                        if (tecnicos == null || tecnicos.isEmpty()){
                            System.out.println("Primeiro Faça a Inserção de Algum Técnico");
                            return;
                        }
                        else {
                            for (Tecnicos tecnico : tecnicos){
                                System.out.println("Id: " + tecnico.getId() +
                                        "\nNome: " + tecnico.getNome() +
                                        "\nEspecialidade: " + tecnico.getEspecialidade() +
                                        "\n--------------------------------------");
                            }
                            System.out.println("Escolha um dos Técnicos Pelo seu Respectivo ID.");
                            int opcaoTecnico = SC.nextInt();
                            for (Tecnicos tecnico : tecnicos){
                                if (tecnico.getId() == opcaoTecnico){
                                    System.out.println("Escolha Valida.");
                                    LocalDate dataManutencao = LocalDate.now();
                                    String status = statusManutencao.PENDENTE.getNome();
                                    OrdemManutencao manutencao = new OrdemManutencao(opcao, opcaoTecnico,
                                            dataManutencao, status);
                                    ORDEM_MANUTENCAO_DAO.cadastrarOrdem(manutencao);
                                    MAQUINAS_DAO.criandoManutencao(opcao);
                                    System.out.println("Ordem de Manutenção Realizada com Sucesso.");
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("Por Favor Escolha uma Opção Valida.");
                        cadastrarManutencao();
                    }
                }

            }
        }catch (SQLException e) {
            System.out.println("Erro ao Acessar o Banco de Dados");
            e.printStackTrace();
        }
    }

    public static void associar() {
        ArrayList<OrdemManutencao> ordens = new ArrayList<>();
        ArrayList<Pecas> pecas = new ArrayList<>();
        try {
            ordens = ORDEM_MANUTENCAO_DAO.listarOrdens();
        }catch (SQLException e) {
            System.out.println("Erro ao Acessar o Banco de Dados");
            e.printStackTrace();
        }
        if (ordens == null || ordens.isEmpty()){
            System.out.println("Primeiro Insira uma Ordem.");
            return;
        }
        for (OrdemManutencao ordem : ordens){
            System.out.println("ID: " + ordem.getId() +
                    "\nData: " + ordem.getDataManutencao() +
                    "\nStatus: " + ordem.getStatus() +
                    "\n--------------------------------------");
        }
        System.out.println("Escolha uma das Ordens Pelo seu Respectivo ID. ");
        int opcaoOrdens = SC.nextInt();
        for (OrdemManutencao ordem : ordens){
            if (ordem.getId() == opcaoOrdens){
                System.out.println("Opção Valida");
                try {
                    pecas = PECAS_DAO.listarPecas();
                }catch (SQLException e) {
                    System.out.println("Erro ao Acessar o Banco de Dados");
                    e.printStackTrace();
                }
                if (pecas == null || pecas.isEmpty()){
                    System.out.println("Primeiro Insira uma Peça");
                    return;
                }
                int resposta = 1;
                do {
                    for (Pecas peca : pecas){
                        System.out.println("ID: " + peca.getId() +
                                "\nNome: " + peca.getNome() +
                                "\nEstoque: " + peca.getEstoque() +
                                "\n---------------------------------------------");
                    }
                    System.out.println("Escolha uma Peça Pelo seu Respectivo ID. ");
                    int opcaoPeca = SC.nextInt();
                    for (Pecas peca : pecas) {
                        if (peca.getId() == opcaoPeca){
                            System.out.println("Peça escolhida: " +
                                    "\nNome: " + peca.getNome() +
                                    "\n Estoque: " + peca.getEstoque() +
                                    "\n----------------------------\n");
                            System.out.println("Informe a Quantidade de Peças que Serão Necessárias. ");
                            int qtdPecas = SC.nextInt();
                            if (qtdPecas < 0 ) {
                                System.out.println("Não é Permitido Realizar um Pedido de Peças Negativo Recomeçe o Processo");
                                associar();
                            }
                            OrdemPeca ordemPeca = new OrdemPeca(opcaoOrdens, opcaoPeca, qtdPecas);
                            try {
                                ORDEM_PECA_DAO.cadastrarOrdemPeca(ordemPeca);
                                System.out.println("Peça Adicionada com Sucesso");
                            }catch (SQLException e) {
                                System.out.println("Erro ao Acessar o Banco de Dados");
                                e.printStackTrace();
                            }
                            System.out.println("""
                                    Você Deseja Adicionar Mais Alguma Peça Para Essa Mesma Ordem??
                                    1 - Sim Desejo
                                    2 - Não Desejo
                                    """);
                            resposta = SC.nextInt();
                        }
                    }
                }while (resposta == 1);

            }
        }

    }

    public static void executarManutencao() {
        ArrayList<OrdemPeca> ordens = new ArrayList<>();
        Pecas pecas = null;
        try {
            ordens = ORDEM_PECA_DAO.listarOrdens();
        }catch (SQLException e) {
            System.out.println("Erro ao Acessar o Banco de Dados");
            e.printStackTrace();
        }
        if (ordens == null || ordens.isEmpty()){
            System.out.println("Primeiro Insira uma Ordem.");
            return;
        }
        System.out.println("Aqui Está as Ordens com Manutenção Pendente.");
        for (OrdemPeca ordem : ordens){
            System.out.println("ID Ordem: " + ordem.getIdOrdem() +
                    "\nID Peça: " + ordem.getIdPeca() +
                    "\nQuantidade: " + ordem.getQuantidade() +
                    "\n--------------------------------------");
        }
        System.out.println("Escolha uma das Ordens Pelo seu Respectivo ID. ");
        int opcaoOrdens = SC.nextInt();
        for (OrdemPeca ordem : ordens) {
            if (ordem.getIdOrdem() == opcaoOrdens) {
                try {
                    int idPeca = ordem.getIdPeca();
                    pecas = PECAS_DAO.buscarPecas(idPeca);
                }catch (SQLException e) {
                    System.out.println("Erro ao Acessar o Banco de Dados");
                    e.printStackTrace();
                }
                if (pecas == null){
                    System.out.println("Peça Não Encontrada");
                    return;
                }
                if (ordem.getQuantidade() < pecas.getEstoque()){
                    try {
                        PECAS_DAO.atualizarEstoque(pecas, ordem);
                        ORDEM_MANUTENCAO_DAO.atualizarOrdem(ordem);
                        listarOrdemManutencao(ordem);
                        System.out.println("Estoque Atualizado e Ordem de Manutenção Marcada Como Executada");
                    }catch (SQLException e) {
                        System.out.println("Erro ao Acessar o Banco de Dados");
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Estoque Insuficiente");
                }
            }
        }
    }

    public static void listarOrdemManutencao (OrdemPeca ordem) {
        OrdemManutencao ordemManutencao = null;
        try {
            ordemManutencao = ORDEM_MANUTENCAO_DAO.buscarOrdem(ordem);
        }catch (SQLException e) {
            System.out.println("Erro ao Acessar o Banco de Dados");
            e.printStackTrace();
        }
        if (ordemManutencao == null){
            System.out.println("Nenhuma Ordem de Manutenção Encontrada.");
            return;
        }
        try {
            MAQUINAS_DAO.atualizarStatus(ordemManutencao);
            System.out.println("Maquina Está Operacional Novamente.");
        }catch (SQLException e) {
            System.out.println("Erro ao Acessar o Banco de Dados");
            e.printStackTrace();
        }
    }
}