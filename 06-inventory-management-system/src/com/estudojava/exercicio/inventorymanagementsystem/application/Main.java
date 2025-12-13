package com.estudojava.exercicio.inventorymanagementsystem.application;

import com.estudojava.exercicio.inventorymanagementsystem.model.Alimento;
import com.estudojava.exercicio.inventorymanagementsystem.model.Eletronico;
import com.estudojava.exercicio.inventorymanagementsystem.model.Roupa;
import com.estudojava.exercicio.inventorymanagementsystem.repository.ProdutoRepository;
import com.estudojava.exercicio.inventorymanagementsystem.service.InventarioService;
import com.estudojava.exercicio.inventorymanagementsystem.util.RelatorioUtil;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repository = new ProdutoRepository();
        InventarioService service = new InventarioService(repository);

        System.out.println("SISTEMA DE INVENTÁRIO");
        popularDados(service);
        demostrarBuscas(service);
        demonstrarOrdenacoes(service);
        demonstrarRelatorios(service);

    }

    private static void popularDados(InventarioService service) {
        System.out.println("Adicionando produtos...\n");

        try {
            service.adicionarProduto(new Eletronico("Notebook Dell", 3500.00, 5, 24));
            service.adicionarProduto(new Eletronico("Mouse Logitech", 50.00, 20, 12));
            service.adicionarProduto(new Eletronico("Teclado Mecânico", 350.00, 8, 12));
            service.adicionarProduto(new Eletronico("Monitor LG 27\"", 1200.00, 3, 36));
            service.adicionarProduto(new Eletronico("Webcam HD", 180.00, 15, 12));

            service.adicionarProduto(new Alimento("Arroz 5kg", 25.00, 100, LocalDate.of(2025, 12, 31)));
            service.adicionarProduto(new Alimento("Feijão 1kg", 8.50, 50, LocalDate.of(2025, 11, 30)));
            service.adicionarProduto(new Alimento("Macarrão 500g", 4.50, 200, LocalDate.of(2026, 1, 15)));
            service.adicionarProduto(new Alimento("Açúcar 1kg", 3.20, 80, LocalDate.of(2026, 6, 30)));

            service.adicionarProduto(new Roupa("Camiseta Básica", 29.90, 50, "M"));
            service.adicionarProduto(new Roupa("Calça Jeans", 89.90, 30, "42"));
            service.adicionarProduto(new Roupa("Jaqueta de Couro", 299.00, 10, "G"));
            service.adicionarProduto(new Roupa("Tênis Esportivo", 199.00, 25, "40"));

            System.out.println("✓ Produtos adicionados com sucesso!");

        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());

        }
    }

    private static void demostrarBuscas(InventarioService service) {
        RelatorioUtil.imprimirCabecalho("DEMONSTRAÇÃO DE BUSCAS");

        RelatorioUtil.imprimirLista("Roupas: ", service.buscarPorCategoria("Roupa"));
        RelatorioUtil.imprimirLista("Produtos abaixo de R$100:", service.buscarAbaixoPreco(100));
        RelatorioUtil.imprimirLista("Estoque baixo (< 10):", service.buscarEstoqueBaixo(10));
        RelatorioUtil.imprimirLista("Faixa R$50 - R$200:", service.buscarPorFaixaPreco(50, 200));
    }

    private static void demonstrarOrdenacoes(InventarioService service) {
        RelatorioUtil.imprimirCabecalho("DEMONSTRAÇÃO DE ORDENAÇÕES");

        RelatorioUtil.imprimirLista("Ordem por nome:", service.ordenarPorNome()
                .subList(0, Math.min(5, service.listarTodosProdutos().size())));

        RelatorioUtil.imprimirLista("Top 5 mais caros:", service.topNMaisCaros(5));
    }

    private static void demonstrarRelatorios(InventarioService service) {
        RelatorioUtil.imprimirRelatorioCompleto(
                service.listarTodosProdutos().size(),
                service.calcularQuantidadeTotal(),
                service.calcularValorTotalInventario(),
                service.calcularPrecoMedio(),
                service.contarPorCategoria(),
                service.valorTotalPorCategoria(),
                service.obterEstatisticasPreco()
        );
    }
}
