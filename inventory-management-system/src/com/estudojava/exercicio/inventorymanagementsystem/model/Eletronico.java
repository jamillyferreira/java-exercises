package com.estudojava.exercicio.inventorymanagementsystem.model;

public class Eletronico implements Produto {

    private String nome;
    private double preco;
    private int quantidade;
    private int garantiaMeses;

    public Eletronico() {
    }

    public Eletronico(String nome, double preco, int quantidade, int garantiaMeses) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.garantiaMeses = garantiaMeses;
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
        return "Eletrônico";
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    @Override
    public String toString() {
        return String.format("%s - R$%.2f (Qtd: %d, Garantia: %d meses)",
                nome, preco, quantidade, garantiaMeses);
    }
}
