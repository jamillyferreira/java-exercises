package com.estudojava.exercicio.inventorymanagementsystem.model;

import java.time.LocalDate;

public class Alimento implements Produto{
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDate dataValidade;

    public Alimento() {
    }

    public Alimento(String nome, double preco, int quantidade, LocalDate dataValidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
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
        return "Alimento";
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    @Override
    public String toString() {
        return String.format("%s - R$%.2f (Qtd: %d, Validade: %s)",
                nome, preco, quantidade, dataValidade);
    }
}
