package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl implements ProdutoService{
    private static final ProdutoRepositoryImpl PRODUTO_REPOSITORY = new ProdutoRepositoryImpl();

    @Override
    public Produto cadastrarProduto(Produto produto) throws Exception {
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser positivo.");
        }
        return PRODUTO_REPOSITORY.save(produto);
    }

    @Override
    public List<Produto> listarProdutos() throws SQLException {
        return PRODUTO_REPOSITORY.findAll();
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        return PRODUTO_REPOSITORY.findById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto, int id) throws SQLException {
        return PRODUTO_REPOSITORY.update(produto);
    }

    @Override
    public boolean excluirProduto(int id) throws SQLException {
        return PRODUTO_REPOSITORY.deleteById(id);
    }
}
