package org.example.Main;

import org.example.DAO.EquipamentoDAO;
import org.example.DAO.FornecedorDAO;
import org.example.Model.Equipamento;
import org.example.Model.Fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static final Scanner SC = new Scanner(System.in);
    static final FornecedorDAO FORNECEDOR_DAO = new FornecedorDAO();
    static final EquipamentoDAO EQUIPAMENTO_DAO = new EquipamentoDAO();

    public static void main(String[] args) {
        int tentativas = 3;
        inicio(tentativas);
    }

    public static void inicio (int tentativas) {
        while (true){
            System.out.println("""
                    1 - Cadastrar Fornecedor
                    2 - Buscar Fornecedor Por Id
                    3 - Listar Todos Os Fornecedores
                    4 - Atualizar Fornecedor
                    5 - Deletar Fornecedor
                    6 - Cadastrar Equipamento
                    7 - Buscar Equipamento Por Id
                    8 - Listar Todos Os Equipamentos Por Id Do Fornecedor
                    9 - Atualizar Equipamento
                    10 - Deletar Equipamento
                    
                    
                    0 - Sair Do Sistema
                    """);
            int opcao = SC.nextInt();
            SC.nextLine();
            switch (opcao){
                case 1:{
                    cadastrarFornecedor();
                    break;
                }
                case 2:{
                    buscarFornecedor(tentativas);
                    break;
                }
                case 3:{
                    listarFornecedor(tentativas);
                    break;
                }
                case 4:{
                    atualizarFornecedor(tentativas);
                    break;
                }
                case 5:{
                    deletarFornecedor(tentativas);
                    break;
                }
                case 6:{
                    cadastrarEquipamento(tentativas);
                    break;
                }
                case 7:{
                    buscarEquipamento(tentativas);
                    break;
                }
                case 8:{
                    escolhaIdFornecedor(tentativas);
                    break;
                }
                case 9:{
                    atualizarEquipamento(tentativas);
                    break;
                }
                case 10:{
                    deletarEquipamento(tentativas);
                    break;
                }
                case 0:{
                    System.exit(0);
                }
                default:{
                    System.out.println("Escolha uma Opção Valida");
                    break;
                }
            }
        }
    }

    public static void cadastrarFornecedor () {
        System.out.println("Insira o Nome do Fornecedor: ");
        String nome = SC.nextLine();
        if (verificarNome(nome)) {
            System.out.println("Insira O CNPJ Do Fornecedor: ");
            String cnpj = SC.nextLine();
            if (verificarCnpj(cnpj)){
                Fornecedor fornecedor = new Fornecedor(nome, cnpj);
                try {
                    FORNECEDOR_DAO.cadastrarFornecedor(fornecedor);
                    System.out.println("Cadastro Realizado Com Sucesso. ");
                } catch (SQLException e) {
                    System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void buscarFornecedor (int tentativas) {
        Fornecedor fornecedor = null;
        System.out.println("Por Favor Selecione Um Fornecedor Pelo Seu Respectivo ID.");
        int idEscolhido = SC.nextInt();
        SC.nextLine();
        if (verificarExistenciaFornecedor(idEscolhido)){
            try {
                fornecedor = FORNECEDOR_DAO.buscarPorId(idEscolhido);
            } catch (SQLException e) {
                System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                e.printStackTrace();
            }
            if (fornecedor != null && fornecedor.getId() == idEscolhido) {
                System.out.println("ID: " + fornecedor.getId() +
                        "Nome: " + fornecedor.getNome() +
                        "CNPJ: " + fornecedor.getCnpj());
            }
        }
    }

    public static void listarFornecedor (int tentativas) {
        ArrayList <Fornecedor> fornecedores = new ArrayList<>();
        try {
            fornecedores = FORNECEDOR_DAO.listarFornecedor();
        } catch (SQLException e) {
            System.out.println("Erro Ao Acessa O Banco De Dados!!!");
            e.printStackTrace();
        }
        if (fornecedores == null || fornecedores.isEmpty()){
            System.out.println("Nenhum Fornecedor Cadastrado, Por Favor Realize Um Cadastro");
            inicio(tentativas);
        }
        else {
            for (Fornecedor fornecedor : fornecedores){
                System.out.println("ID: " + fornecedor.getId() +
                        "Nome: " + fornecedor.getNome() +
                        "CNPJ: " + fornecedor.getCnpj());
            }
        }
    }

    public static void atualizarFornecedor (int tentativas) {
        System.out.println("""
                1 - Atualizar Nome
                2 - Atualizar CNPJ
                3 - Atualizar Ambos
                
                0 - Voltar
                
                Escolha O Que Deseja Atualizar.
                """);
        int escolha = SC.nextInt();
        switch (escolha){
            case 1:{
                atualizarNomeFornecedor(tentativas);
                break;
            }
            case 2:{
                atualizarCnpjFornecedor(tentativas);
                break;
            }
            case 3:{
                atualizarTudoFornecedor(tentativas);
                break;
            }
            default:{
                System.out.println("Escolha uma Opção Valida Por Favor");
                inicio(tentativas);
            }
        }
    }

    public static void deletarFornecedor (int tentativas) {
        listarFornecedor(tentativas);
        System.out.println("Escolha Um Fornecedor Para Deletar Pelo Seu Respectivo Id. " +
                "\nCaso Não Deseje Deletar Nenhum Fornecedor Digite 0 Para Retornar. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if (idEscolha == 0 ) {
            inicio(tentativas);
        }
        if (verificarExistenciaFornecedor(idEscolha)) {
            System.out.println("Para Deletar Digite A Palavra Chave. "); // Palavra Chave É Beatriz
            String palavraChave = SC.nextLine();
            if (verificarPalavraChave(palavraChave, tentativas)) {
                try {
                    FORNECEDOR_DAO.deletarFornecedor(idEscolha);
                    System.out.println("Fornecedor Deletado Com Sucesso. ");
                } catch (SQLException e) {
                    System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void cadastrarEquipamento (int tentativas) {
        System.out.println("Insira O Nome Do Equipamento. ");
        String nome = SC.nextLine();
        verificarNome(nome);
        System.out.println("Insira O Número De Série Do Equipamento. ");
        String numeroSerie = SC.nextLine();
        verificarNumero(numeroSerie);
        listarFornecedor(tentativas);
        System.out.println("Escolha Um Fornecedor Pelo Seu Respectivo ID. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        Equipamento equipamento = new Equipamento(nome, numeroSerie, idEscolha);
        if (verificarExistenciaFornecedor(idEscolha)){
            try {
                EQUIPAMENTO_DAO.cadastrarEquipamento(equipamento);
            }
            catch (SQLException e) {
                System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                e.printStackTrace();
            }
        }
    }

    public static void buscarEquipamento (int tentativas) {
        Equipamento equipamento = null;
        System.out.println("Escolha Um Equipamento Pelo Seu Respectivo ID. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if (verificarExistenciaEquipamento(idEscolha)){
            try {
                equipamento = EQUIPAMENTO_DAO.buscarPorId(idEscolha);
            }
            catch (SQLException e) {
                System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                e.printStackTrace();
            }
            if (equipamento != null ) {
                System.out.println("ID: " + equipamento.getId() +
                        "\nNome: " + equipamento.getNome() +
                        "\nNúmero De Série: " + equipamento.getNumeroSerie() +
                        "\nID Do Fornecedor: " + equipamento.getIdFornecedor() +
                        "\n-------------------------------------------");
            }
            else {
                System.out.println("Equipamento Não Encontrado. ");
            }
        }
    }

    public static void escolhaIdFornecedor (int tentativas) {
        listarFornecedor(tentativas);
        System.out.println("Escolha Um Fornecedor Para Deletar Pelo Seu Respectivo Id. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if (verificarExistenciaFornecedor(idEscolha)){
            listarEquipamentoPeloId(tentativas, idEscolha);
        }
    }

    public static void listarEquipamentoPeloId (int tentativas, int idEscolha) {
        ArrayList <Equipamento> equipamentos = null;
        try {
            equipamentos = EQUIPAMENTO_DAO.listarEquipamentoPeloID(idEscolha);
        }
        catch (SQLException e) {
            System.out.println("Erro Ao Acessa O Banco De Dados!!!");
            e.printStackTrace();
        }
        if (equipamentos == null || equipamentos.isEmpty()){
            System.out.println("Primeiro Insira Um Equipamento Para Depois Listar Ele");
            inicio(tentativas);
        }
        else{
            for (Equipamento equipamento : equipamentos){
                System.out.println("ID: " + equipamento.getId() +
                        "\nNome: " + equipamento.getNome() +
                        "\nNúmero De Série: " + equipamento.getNumeroSerie() +
                        "\nID Do Fornecedor: " + equipamento.getIdFornecedor() +
                        "\n-------------------------------------------");
            }
        }
    }

    public static void listarEquipamento (int tentativas) {
        ArrayList <Equipamento> equipamentos = null;
        try {
            equipamentos = EQUIPAMENTO_DAO.listarEquipamento();
        }
        catch (SQLException e) {
            System.out.println("Erro Ao Acessa O Banco De Dados!!!");
            e.printStackTrace();
        }
        if (equipamentos == null || equipamentos.isEmpty()){
            System.out.println("Primeiro Insira Um Equipamento Para Depois Listar Ele");
            inicio(tentativas);
        }
        else{
            for (Equipamento equipamento : equipamentos){
                System.out.println("ID: " + equipamento.getId() +
                        "\nNome: " + equipamento.getNome() +
                        "\nNúmero De Série: " + equipamento.getNumeroSerie() +
                        "\nID Do Fornecedor: " + equipamento.getIdFornecedor() +
                        "\n-------------------------------------------");
            }
        }
    }

    public static void atualizarEquipamento (int tentativas) {
        listarEquipamento(tentativas);
        System.out.println("Escolha Um Equipamento Para Atualizar Pelo Seu Respectivo Id. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if (verificarExistenciaEquipamento(tentativas)) {
            System.out.println("Insira O Novo Nome Para O Equipamento: ");
            String nome = SC.nextLine();
            if (verificarNome(nome)) {
                try {
                    EQUIPAMENTO_DAO.atualizarNomeEquipamento(nome,idEscolha);
                    System.out.println("Nome Do Equipamento Atualizado Com Sucesso. ");
                }
                catch (SQLException e) {
                    System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deletarEquipamento (int tentativas) {
        listarEquipamento(tentativas);
        System.out.println("Escolha Um Equipamento Para Deletar Pelo Seu Respectivo Id. " +
                "\nCaso Não Deseje Deletar Nenhum Fornecedor Digite 0 Para Retornar. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if (idEscolha == 0 ) {
            inicio(tentativas);
        }
        if (verificarExistenciaEquipamento(idEscolha)) {
            System.out.println("Para Deletar Digite A Palavra Chave. "); // Palavra Chave É Beatriz
            String palavraChave = SC.nextLine();
            if (verificarPalavraChave(palavraChave, tentativas)) {
                try {
                    EQUIPAMENTO_DAO.deletarEquipamento(idEscolha);
                    System.out.println("Equipamento Deletado Com Sucesso. ");
                } catch (SQLException e) {
                    System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean verificarNome (String nome) {
        if (nome == null) {
            System.out.println("Por Favor Insira Um Nome Valido");
            return false;
        }
        else {
            System.out.println("Entrada Valida Continue A Inserção");
            return true;
        }
    }

    public static boolean verificarCnpj (String cnpj) {
        try{
            if (cnpj == null || cnpj.length() != 14 && !FORNECEDOR_DAO.verificarEntrada(cnpj)) {
                System.out.println("Por Favor Um CNPJ Valido (14 números)");
                return false;
            }
            else if (FORNECEDOR_DAO.verificarEntrada(cnpj)) {
                System.out.println("Entrada Valida Continue A Inserção");
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("Erro Ao Acessa O Banco De Dados!!!");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean verificarPalavraChave (String palavraChave, int tentativas) {
        String chave = palavraChave.toLowerCase();
        if (chave.equals("beatriz")){
            System.out.println("Chave De Segurança Correta Prossiga");
            return true;
        }
        else {
            tentativas -= 1;
            System.out.println("Chave De Segurança Inserida Incorretamente Você Tem Mais " + tentativas + " Tentativas");
            deletarFornecedor(tentativas);
            return false;
        }
    }

    public static boolean verificarExistenciaFornecedor (int idEscolha) {
        ArrayList <Fornecedor> fornecedores = new ArrayList<>();
        try {
            fornecedores = FORNECEDOR_DAO.listarFornecedor();
        } catch (SQLException e) {
            System.out.println("Erro Ao Acessa O Banco De Dados!!!");
            e.printStackTrace();
        }
        if (fornecedores == null || fornecedores.isEmpty()){
            System.out.println("Cadastre Um Fornecedor Primeiro. ");
            return false;
        }
        try {
            for (Fornecedor fornecedor : fornecedores){
                if (fornecedor.getId() == idEscolha){
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            System.out.println("Id do Fornecedor não encontrado!");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verificarNumero (String numeroSerie) {
        try {
            if (numeroSerie == null && !EQUIPAMENTO_DAO.verificarNumero(numeroSerie)) {
                System.out.println("Por Favor Insira Um Número De Série Valido");
                return false;
            }
            else if (EQUIPAMENTO_DAO.verificarNumero(numeroSerie)){
                System.out.println("Entrada Valida Continue A Inserção");
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("Erro Ao Acessa O Banco De Dados!!!");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean verificarExistenciaEquipamento (int idEscolha) {
        ArrayList <Equipamento> equipamentos = null;
        try {
            equipamentos = EQUIPAMENTO_DAO.listarEquipamento();
        }
        catch (SQLException e) {
            System.out.println("Erro Ao Acessa O Banco De Dados!!!");
            e.printStackTrace();
        }
        if (equipamentos == null || equipamentos.isEmpty()){
            System.out.println("Insira Um Equipamento Antes De Listar Eles");
            return false;
        }
        try {
            for (Equipamento equipamento : equipamentos){
                if (equipamento.getId() == idEscolha){
                    return true;
                }
            }
            return false;
        }
        catch (RuntimeException e) {
            System.out.println("Id do Fornecedor não encontrado!");
            e.printStackTrace();
        }
        return false;
    }

    public static void atualizarNomeFornecedor (int tentativas) {
        listarFornecedor(tentativas);
        System.out.println("Escolha Um Fornecedor Pelo Seu ID Para Alterar Seu Nome. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if(verificarExistenciaFornecedor(idEscolha)) {
            System.out.println("Informe O Novo Nome Do Fornecedor. ");
            String novoNome = SC.nextLine();
            if (verificarNome(novoNome)) {
                try {
                    FORNECEDOR_DAO.atualizarNomeFornecedor(novoNome, idEscolha);
                    System.out.println("Atualização Realizada Com Sucesso. ");
                } catch (SQLException e) {
                    System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void atualizarCnpjFornecedor (int tentativas) {
        listarFornecedor(tentativas);
        System.out.println("Escolha Um Fornecedor Pelo Seu ID Para Alterar Seu Cnpj. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if (verificarExistenciaFornecedor(idEscolha)){
            System.out.println("Informe O Novo Cnpj Do Fornecedor. ");
            String novoCnpj = SC.nextLine();
            if (verificarCnpj(novoCnpj)) {
                try {
                    FORNECEDOR_DAO.atualizarCnpjFornecedor(novoCnpj, idEscolha);
                    System.out.println("CNPJ Do Fornecedor Foi Atualizado Com Sucesso");
                } catch (SQLException e) {
                    System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void atualizarTudoFornecedor (int tentativas) {
        listarFornecedor(tentativas);
        System.out.println("Escolha Um Fornecedor Pelo Seu ID Para Alterar Seu Cnpj. ");
        int idEscolha = SC.nextInt();
        SC.nextLine();
        if (verificarExistenciaFornecedor(idEscolha)){
            System.out.println("Informe O Novo Nome Do Fornecedor. ");
            String novoNome = SC.nextLine();
            if (verificarNome(novoNome)) {
                System.out.println("Informe O Novo Cnpj Do Fornecedor. ");
                String novoCnpj = SC.nextLine();
                if (verificarCnpj(novoCnpj)) {
                    try {
                        FORNECEDOR_DAO.atualizarTudoFornecedor(novoCnpj, novoNome, idEscolha);
                        System.out.println("Fornecedor Atualizado Com Sucesso. ");
                    } catch (SQLException e) {
                        System.out.println("Erro Ao Acessa O Banco De Dados!!!");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}