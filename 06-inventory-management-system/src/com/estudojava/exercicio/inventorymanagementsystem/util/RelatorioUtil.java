package com.estudojava.exercicio.inventorymanagementsystem.util;

import com.estudojava.exercicio.inventorymanagementsystem.model.Produto;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

public class RelatorioUtil {
    public static void imprimirCabecalho(String titulo) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(titulo);
        System.out.println("=".repeat(60));
    }

    public static void imprimirLista(String titulo, List<Produto> produtos) {
        System.out.println("\n" + titulo);
        if (produtos.isEmpty()) {
            System.out.println("  (Nenhum produto encontrado)");
        } else {
            produtos.forEach(p -> System.out.println(" - " + p));
        }
    }

    public static void imprimirEstatisticas(DoubleSummaryStatistics stats) {
        System.out.println("\n ESTATÍSTICAS DE PREÇOS:");
        System.out.printf(" Menor preço: R$%.2f%n", stats.getMin());
        System.out.printf(" Maior preço: R$%.2f%n", stats.getMax());
        System.out.printf(" Preço médio: R$%.2f%n", stats.getAverage());
        System.out.printf(" Total de produtos: %d%n", stats.getCount());
    }

    public static void imprimirRelatorioCompleto(
            int totalProdutos,
            int quantidadeTotal,
            double valorTotal,
            double precoMedio,
            Map<String, Long> porCategoria,
            Map<String, Double> valorPorCategoria,
            DoubleSummaryStatistics stats) {
        imprimirCabecalho("RELATÓRIO COMPLETO DO INVENTÁRIO");

        System.out.printf("Total de Produtos: %d%n", totalProdutos);
        System.out.printf("Quantidade total em estoque: %d unidades%n", quantidadeTotal);
        System.out.printf("Valor total do inventario: R$%.2f%n", valorTotal);
        System.out.printf("Preço medio: R$%.2f%n", precoMedio);

        System.out.println("Produtos por categorias:");
        porCategoria.forEach((cat, count) ->
                System.out.printf("  %s: %d produtos%n", cat, count)
        );

        System.out.println("\nValor total por categoria:");
        valorPorCategoria.forEach((cat, valor) ->
                System.out.printf("  %s: R$%.2f%n", cat, valor)
        );

        imprimirEstatisticas(stats);
        System.out.println("=".repeat(60));
    }
}
