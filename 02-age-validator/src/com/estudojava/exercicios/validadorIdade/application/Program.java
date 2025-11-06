package com.estudojava.exercicios.validadorIdade.application;

import com.estudojava.exercicios.validadorIdade.entities.Pessoa;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
//            try = tenta executar esse bloco; se der erro (excecao), o catch captura
            System.out.println("=== TESTANDO VALIDADOR DE IDADE ===");
            System.out.print("Digite o seu nome: ");
            String name = scanner.nextLine();

            System.out.print("Digite sua idade: ");
            int age = scanner.nextInt();

            // Aqui o construtor pode lançar IllegalArgumentException
            // se a idade for inválida (menor que 0 ou maior que 150)
            Pessoa pessoa = new Pessoa(name, age);
            System.out.println("\nPessoa criada com sucesso:");
            System.out.println(pessoa);

        } catch (IllegalArgumentException e) {
//            catch = pega o erro se ele acontecer no try
//            e.getMessage() mostra a mensagem que defini na classe Pessoa
            System.out.println("\nERRO: " + e.getMessage());
        }

        scanner.close();

    }
}

