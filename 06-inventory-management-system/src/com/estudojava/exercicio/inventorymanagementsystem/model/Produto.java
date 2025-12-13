package com.estudojava.exercicio.inventorymanagementsystem.model;

public interface Produto {
    String getNome();

    double getPreco();

    int getQuantidade();
    void setQuantidade(int quantidade);

    String getCategoria();

    // metodo default, assim podemos adicionar logica comum sem quebrar classes existentes
    default double getValorTotal() {
        return getPreco() * getQuantidade();
    }
}
