package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDao {
    public void cadastrarProduto(String nome,Double preco,int qtd) throws SQLException {
        String command = """
                INSERT INTO produto
                (nome,
                preco,
                quantidade)
                VALUES
                (?,?,?)
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setString(1,nome);
            stmt.setDouble(2,preco);
            stmt.setInt(3,qtd);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Produto> listarProdutos()throws SQLException{
        ArrayList <Produto> produtos = new ArrayList<>();
        String command= """
                SELECT  id,
                        nome,
                        preco,
                        quantidade
                FROM produto
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                ));
            }
            return produtos;
        }
    }

    public void atualizarPreco (Double preco, int id)throws SQLException{
        String command = """
                UPDATE produto
                SET preco = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setDouble(1,preco);
            stmt.setInt(2,id);
            stmt.executeUpdate();
        }
    }

    public void excluirProduto (int id) throws SQLException {
        String command = """
                DELETE
                FROM produto
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
