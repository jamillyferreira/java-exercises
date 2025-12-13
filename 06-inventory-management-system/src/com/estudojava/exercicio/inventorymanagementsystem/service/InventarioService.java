package com.estudojava.exercicio.inventorymanagementsystem.service;

import com.estudojava.exercicio.inventorymanagementsystem.model.Produto;
import com.estudojava.exercicio.inventorymanagementsystem.repository.ProdutoRepository;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.security.cert.CertPath;
import java.util.*;
import java.util.stream.Collectors;

public class InventarioService {
    private ProdutoRepository repository;

    public InventarioService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void adicionarProduto(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        if (repository.existe(produto.getNome())) {
            throw new IllegalArgumentException("Produto já existe no inventário");
        }
        repository.salvar(produto);
    }

    public Optional<Produto> buscarProduto(String nome) {
        return repository.buscarPorNome(nome);
    }

    public List<Produto> listarTodosProdutos() {
        return repository.buscarTodos();
    }

    public boolean atualizarEstoque(String nome, int novaQuantidade) {
        Optional<Produto> produtoOptional = repository.buscarPorNome(nome);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setQuantidade(novaQuantidade);
            return true;
        }
        return false;
    }

    public boolean removerProduto(String nome) {
        return repository.remover(nome);
    }

    // ===== BUSCAS E FILTROS =====
    public List<Produto> buscarPorCategoria(String categoria) {
        return repository.buscarTodos().stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria)).toList();
    }

    public List<Produto> buscarAbaixoPreco(double precoMaximo) {
        return repository.buscarTodos().stream()
                .filter(p -> p.getPreco() < precoMaximo).toList();
    }

    public List<Produto> buscarAcimaPreco(double precoMinimo) {
        return repository.buscarTodos().stream()
                .filter(p -> p.getPreco() > precoMinimo).toList();
    }

    public List<Produto> buscarPorFaixaPreco(double min, double max) {
        return repository.buscarTodos().stream()
                .filter(p -> p.getPreco() >= min && p.getPreco() <= max).toList();
    }

    public List<Produto> buscarEstoqueBaixo(int limiteMinimo) {
        return repository.buscarTodos().stream()
                .filter(p -> p.getQuantidade() < limiteMinimo)
                .sorted(Comparator.comparingInt(Produto::getQuantidade)).toList();
    }

    // ===== ORDENAR =====
    public List<Produto> ordenarPorNome() {
        return repository.buscarTodos().stream()
                .sorted(Comparator.comparing(Produto::getNome)).toList();
    }

    public List<Produto> ordenarPorPreco(boolean crescente) {
        Comparator<Produto> comparator = Comparator.comparingDouble(Produto::getPreco);
        if (!crescente) {
            comparator = comparator.reversed();
        }
        return repository.buscarTodos().stream().sorted(comparator).toList();
    }

    public List<Produto> ordenarPorValorTotal() {
        return repository.buscarTodos().stream()
                .sorted(Comparator.comparingDouble(Produto::getValorTotal).reversed()).toList();
    }

    public double calcularValorTotalInventario() {
        return repository.buscarTodos().stream().mapToDouble(Produto::getValorTotal).sum();
    }

    public double calcularPrecoMedio() {
        return repository.buscarTodos().stream()
                .mapToDouble(Produto::getPreco).average().orElse(0.0);
    }

    public int calcularQuantidadeTotal() {
        return repository.buscarTodos().stream().mapToInt(Produto::getQuantidade).sum();
    }

    public Map<String, List<Produto>> agruparPorCategoria() {
        return repository.buscarTodos().stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));
    }

    public Map<String, Long> contarPorCategoria() {
        return repository.buscarTodos().stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
    }

    public Map<String, Double> valorTotalPorCategoria() {
        return repository.buscarTodos().stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.summingDouble(Produto::getValorTotal)));
    }

    public List<Produto> topNMaisCaros(int n) {
        return repository.buscarTodos().stream()
                .sorted(Comparator.comparingDouble(Produto::getPreco).reversed())
                .limit(n).toList();
    }

    public DoubleSummaryStatistics obterEstatisticasPreco() {
        return repository.buscarTodos().stream()
                .mapToDouble(Produto::getPreco).summaryStatistics();
    }

    public Set<String> listarCategorias() {
        return repository.listarCategorias();
    }
}
