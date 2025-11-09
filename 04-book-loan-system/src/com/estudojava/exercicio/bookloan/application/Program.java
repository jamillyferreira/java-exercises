package com.estudojava.exercicio.bookloan.application;

import com.estudojava.exercicio.bookloan.entities.Book;
import com.estudojava.exercicio.bookloan.entities.Library;
import com.estudojava.exercicio.bookloan.entities.User;
import com.estudojava.exercicio.bookloan.exceptions.BookNotFoundException;
import com.estudojava.exercicio.bookloan.exceptions.BookUnavailableException;
import com.estudojava.exercicio.bookloan.exceptions.LoanLimitsException;

public class Program {
    public static void main(String[] args) {
        System.out.println("SISTEMA DE EMPRESTIMO DE LIVROS");

        Library library = new Library("Biblioteca Central");

        Book book1 = new Book("Clean Code", "Robert Martins", "978-0132350884");
        Book book2 = new Book("Design Patterns", "Gang of Four", "978-0201633612");
        Book book3 = new Book("Refactoring", "Martin Fowler", "978-0134757599");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        User user1 = new User("Ana Silva", "U001");
        User user2 = new User("Pedro Santos", "U002");

        library.showCollection();

        System.out.println("\n--- Teste 1: Empréstimo normal ---");
        try {
            library.lendBook("978-0132350884", user1);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        System.out.println("\n--- Teste 2: Livro Indisponível ---");
        try {
            library.lendBook("978-0132350884", user1);
        } catch (BookUnavailableException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }


        System.out.println("\n--- Teste 3: Empréstar multiplos livros ---");
        try {
            library.lendBook("978-0201633612", user1);
            library.lendBook("978-0134757599", user1);
            System.out.println("Status: " + user1);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        System.out.println("\n--- Teste 4: Limite de empréstimo excedido ---");
        try {
            library.lendBook("978-0135957059", user1);
        } catch (LoanLimitsException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        System.out.println("\n--- Teste 5: Livro não encontrado ---");
        try {
            library.lendBook("978-9999999999", user1);
        } catch (BookNotFoundException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        System.out.println("\n--- Teste 6: Devolução ---");
        try {
            library.returnBook("978-0132350884", user1);
            System.out.println("Status: " + user1);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        System.out.println("\n--- Teste 7: Empréstimo apos devolucao ---");
        try {
            library.lendBook("978-0132350884", user2);
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        library.showCollection();
        System.out.println("\n" + user1);
        System.out.println(user2);
    }
}
