package org.example.repository;

import org.example.model.Produto;
import org.example.util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class ProdutoRepositoryImpl implements ProdutoRepository{

    @Override
    public Produto save(Produto produto) throws SQLException {

        String command = """
                INSERT INTO produto(
                nome,
                preco,
                quantidade,
                categoria)
                VALUES
                (?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement
        (command, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                produto.setId(rs.getInt(1));
                return produto;

            }
        }

        throw new SQLException("Produto não foi salvo no banco");

    }

    @Override
    public List<Produto> findAll() throws SQLException {
        List <Produto> produtos = new ArrayList<>();
        String command = """
                SELECT
                id,
                nome,
                preco,
                quantidade,
                categoria
                FROM produto
                """;

        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement (command)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                var produto = new Produto(

                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("categoria")

                );

                produtos.add(produto);

            }

            return produtos;

        }

    }

    @Override
    public Produto findById(int id) throws SQLException {
        Produto produto;

        String command = """
                SELECT
                id,
                nome,
                preco,
                quantidade,
                categoria
                FROM produto
                WHERE id = ?
                """;

        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                var produtoEncontrado = new Produto(

                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("categoria")

                );

                return produto = produtoEncontrado;

            }
            
            else {

                throw new SQLException("Produto não existe no banco de dados");

            }  
        }
    }

    @Override
    public Produto update(Produto produto) throws SQLException {

        String command = """
                UPDATE produto
                SET 
                nome = ?,
                preco = ?,
                quantidade = ?,
                categoria = ?
                WHERE id = ?
                """;

        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.setInt(5, produto.getId());

            stmt.executeUpdate();

            return produto;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        
        String command = """
                DELETE FROM produto
                WHERE id = ?
                """;

        try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setInt(1, id);
            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas > 0) {
                return true;
            }

            else {
                return false;
            }
        }
    }
}
