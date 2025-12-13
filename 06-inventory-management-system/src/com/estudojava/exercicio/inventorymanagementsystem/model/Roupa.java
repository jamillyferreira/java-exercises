package com.estudojava.exercicio.inventorymanagementsystem.model;

public class Roupa implements Produto {
    private String nome;
    private double preco;
    private int quantidade;
    private String tamanho;

    public Roupa() {
    }

    public Roupa(String nome, double preco, int quantidade, String tamanho) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tamanho = tamanho;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String getCategoria() {
        return "Roupa";
    }

    public String getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        return String.format("%s - R$%.2f (Qtd: %d, Tamanho: %s meses)",
                nome, preco, quantidade, tamanho);
    }
}
