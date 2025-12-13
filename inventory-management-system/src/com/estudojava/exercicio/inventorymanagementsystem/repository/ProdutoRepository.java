package com.estudojava.exercicio.inventorymanagementsystem.repository;

import com.estudojava.exercicio.inventorymanagementsystem.model.Produto;

import java.util.*;

public class ProdutoRepository {
    private List<Produto> produtos;
    private Map<String, Produto> produtosPorNome;
    private Set<String> categorias;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
        this.produtosPorNome = new HashMap<>();
        this.categorias = new HashSet<>();
    }

    //create -> salvar
    public void salvar(Produto produto) {
        produtos.add(produto);
        produtosPorNome.put(produto.getNome(), produto);
        categorias.add(produto.getCategoria());
    }

    // buscar por nome
    public Optional<Produto> buscarPorNome(String nome) {
        return Optional.ofNullable(produtosPorNome.get(nome));
    }

    // buscar todos
    public List<Produto> buscarTodos() {
        return new ArrayList<>(produtos);
    }

    // buscar por categorias
    public List<Produto> buscarPorCategorias(String categoria) {
        List<Produto> resultado = new ArrayList<>();
        for(Produto produto : produtos) {
            if (produto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    //listar categorias
    public Set<String> listarCategorias() {
        return new HashSet<>(categorias);
    }

    // atualizar
    public boolean atualizar(String nome, Produto produtoAtualizado) {
        if(produtosPorNome.containsKey(nome)) {
            Produto antigo = produtosPorNome.get(nome);
            produtos.remove(antigo);

            produtos.add(produtoAtualizado);
            produtosPorNome.put(produtoAtualizado.getNome(), produtoAtualizado);
            categorias.add(produtoAtualizado.getCategoria());

            return true;
        }
        return false;
    }

    //deletar
    public boolean remover(String nome) {
        Produto produto = produtosPorNome.get(nome);
        if(produto != null) {
            produtos.remove(produto);
            produtosPorNome.remove(nome);
            return true;
        }
        return false;
    }

    public int contar() {
        return produtos.size();
    }

    public boolean existe(String nome) {
        return produtosPorNome.containsKey(nome);
    }
}
