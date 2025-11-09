package com.estudojava.exercicio.bookloan.entities;

import com.estudojava.exercicio.bookloan.exceptions.BookNotFoundException;
import com.estudojava.exercicio.bookloan.exceptions.BookUnavailableException;
import com.estudojava.exercicio.bookloan.exceptions.LoanLimitsException;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> collection;

    // ArrayList permite crescimento dinamico: Ideal quando nao sabemos quantos livroes teremos
    public Library(String name) {
        this.name = name;

        // Inicialização no construtor evita NullPointerException ao tentar usar collection
        this.collection = new ArrayList<>();
    }

    public void addBook(Book book) {
        collection.add(book);
        System.out.println("Livro adicionado ao acervo: " + book.getTitle());
    }

    // Lança exceção checked -> quem chamar é OBRIGADO a tratar ou propagar
    public Book searchBookForIsbn(String isbn)
            throws BookNotFoundException {
        for (Book book : collection) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        // Exception como controle de fluxo: "não encontrado" é tratado como situação excepcional
        throw new BookNotFoundException("Livro com ISBN " + isbn + " não encontrado no acervo!");
    }

    public void lendBook(String isbn, User user)
            throws BookNotFoundException, BookUnavailableException, LoanLimitsException {

        System.out.println("\nBuscando livro no acervo...");
        Book book = searchBookForIsbn(isbn);

        // Validação antes de modificar estado, previne inconsistências
        if (!book.isAvailable()) {
            System.out.println("O livro está indisponível");
            throw new BookUnavailableException("O livro " + book.getTitle() + " já está emprestado!");
        }

        System.out.println("Livro encontrado: " + book.getTitle());

        // Delegação de responsabilidade: classe User valida seu próprio limite, Library não precisa saber dessa regra
        user.addBook(book);
        // Mudança de estado coordenada: livro sai da biblioteca E entra na posse do usuário
        book.setAvailable(false); // marca como indisponivel para empréstimo

        System.out.println("Empréstmio concluído!");
        System.out.println("Livro: " + book.getTitle() + " | Para: " + user.getName());
        System.out.println("O usuário tem " + user.getQuantityLoan() + "/3 livros");
    }

    public void returnBook(String isbn, User user)
            throws BookNotFoundException {
        Book book = searchBookForIsbn(isbn);
        user.removeBook(book);
        book.setAvailable(true);

        System.out.println("Devolução realizada com sucesso!");
        System.out.println("Livro: " + book.getTitle());
        System.out.println("Usuáro: " + user.getName());
    }

    public void showCollection() {
        System.out.println("\nAcervo da " + name);
        if (collection.isEmpty()) {
            System.out.println("Nenhum livro no acervo.");
        } else {
            for (int i = 0; i < collection.size(); i++) {
                System.out.println((i + 1) + ". " + collection.get(i));
            }
        }
    }


}
