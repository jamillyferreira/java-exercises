package com.estudojava.exercicio.bookloan.entities;

import com.estudojava.exercicio.bookloan.exceptions.LoanLimitsException;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String id;

    private static final int LOAN_LIMIT = 3;
    private List<Book> borrowedBooks;

    public User(String name, String id) {
        this.name = name;
        this.id = id;

        // IMPORTANTE: criar a lista vazia quando o objeto é criado
        this.borrowedBooks = new ArrayList<>(); // Agora a lista está vazia: []
    }

    public String getName() { return name; }
    public String getId() { return id; }

    // Encapsulamento: expõe apenas a quantidade, nao a lista completa
    // Cliente nao precisa saber que usamos List internamente
    public int getQuantityLoan() {
        return borrowedBooks.size();
    }


    // Validação interna: User conhece e impõe suas proprias regras
    // Library nao precisa saber detalhes sobre limites de emprestimo
    public void addBook(Book book)
            throws LoanLimitsException {

        // Guard clause: valida antes de modificar estado
        if (borrowedBooks.size() >= LOAN_LIMIT) {
            throw new LoanLimitsException("Usuário " + name + "  já possui " + LOAN_LIMIT + " livros emprestados!");
        }
        borrowedBooks.add(book);
    }

    public void removeBook(Book book) {
        borrowedBooks.remove(book);
    }

    // CÓPIA DEFENSIVA: retorna nova lista para proteger a coleção interna
    // Cliente pode modificar a lista retornada sem afetar borrowedBooks original
    // Previne vazamento de referência (encapsulamento)
    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %s) - %d/%d Livros emprestados",
                name, id, borrowedBooks.size(), LOAN_LIMIT);
    }
}
