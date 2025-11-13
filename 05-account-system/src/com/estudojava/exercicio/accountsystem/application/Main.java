package com.estudojava.exercicio.accountsystem.application;

import com.estudojava.exercicio.accountsystem.entities.Account;
import com.estudojava.exercicio.accountsystem.entities.User;
import com.estudojava.exercicio.accountsystem.exceptions.InsufficientBalanceException;
import com.estudojava.exercicio.accountsystem.exceptions.InvalidAccountDataException;
import com.estudojava.exercicio.accountsystem.exceptions.InvalidAmountException;
import com.estudojava.exercicio.accountsystem.exceptions.InvalidUserDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("SISTEMA BANCÁRIO - BEM-VINDO!");

        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            int option = readOption();

            try {
                switch (option) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        checkBalance();
                        break;
                    case 5:
                        transfer();
                        break;
                    case 6:
                        listAccounts();
                        break;
                    case 0:
                        isRunning = false;
                        System.out.println("Encerrando sistema. Até logo!");
                    default:
                        System.out.println("\n Opção inválida. Tente novamente\n");

                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            if (isRunning) {
                pause();
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("--- MENU PRINICIPAL ---");
        System.out.println("1. Criar Conta");
        System.out.println("2. Realizar Depósito");
        System.out.println("3. Realizar Saque");
        System.out.println("4. Consultar Saldo");
        System.out.println("5. Transferir");
        System.out.println("6. Listar Contas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int readOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void createAccount() {
        System.out.println("\n--- Criar nova conta ---");

        try {
            System.out.print("Nome do titular: ");
            String name = scanner.nextLine();

            System.out.print("ID do titular (apenas números): ");
            String id = scanner.nextLine();

            User user = new User(name, id);

            System.out.print("Número da conta (ex: 1234-5): ");
            String numberAccount = scanner.nextLine();

            Account account = new Account(numberAccount, user);
            System.out.println("✓ Conta criada com sucesso!");
            accounts.add(account);

            System.out.print("\nDeseja fazer um depósito inicial (s/n)?: ");
            String response = scanner.nextLine().trim().toUpperCase();

            if (response.equals("S")) {
                System.out.print("Valor do depósito: R$ ");
                double depositValue = Double.parseDouble(scanner.nextLine());
                account.deposit(depositValue);
                System.out.println("✓ Depósito inicial realizado!");
            }

            System.out.println(account);

        } catch (InvalidUserDataException | InvalidAccountDataException e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Erro no depósito inicial: " + e.getMessage());
        }
    }

    private static void deposit() {
        System.out.println("--- Realizar Depósito ---");

        Account account = searchAccount();
        if (account == null) return;
        try {
            System.out.print("Valor do depósito: R$ ");
            double valueDeposit = Double.parseDouble(scanner.nextLine());
            account.deposit(valueDeposit);
        } catch (InvalidAmountException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.println("--- Realizar Saque ---");
        Account account = searchAccount();
        if (account == null) return;

        try {
            System.out.println("Saldo disponível: R$ " + String.format("%.2f", account.getBalance()));
            System.out.print("Valor do saque: R$ ");
            double value = Double.parseDouble(scanner.nextLine());
            account.withdraw(value);

            System.out.println("✓ Saque realizado com sucesso!");
            System.out.print("Novo saldo: R$ " + String.format("%.2f", account.getBalance()));
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void transfer() {
        System.out.println("\n--- Transferência ---");

        System.out.println("\nConta de Origem");
        Account sourceAccount = searchAccount();
        if (sourceAccount == null) return;

        System.out.println("\nConta de Destino");
        Account targetAccount = searchAccount();
        if (targetAccount == null) return;

        try {
            System.out.println("Saldo disponível: R$ " + String.format("%.2f", sourceAccount.getBalance()));
            System.out.print("Valor da transferência: R$ ");
            double value = Double.parseDouble(scanner.nextLine());
            sourceAccount.transfer(targetAccount, value);

            System.out.println("✓ Transferência realizada com sucesso!");
            System.out.println("Origem: " + sourceAccount.getHolder().getName()
                    + " | Saldo atual: R$ " + String.format("%.2f", sourceAccount.getBalance()));
            System.out.println("Destino: " + targetAccount.getHolder().getName()  +
                    "| Valor recebido: R$ " + String.format("%.2f", targetAccount.getBalance()));


        } catch (InvalidAccountDataException | InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Erro :" + e.getMessage());
        }

    }

    // Métodos de consulta
    private static void checkBalance() {
        System.out.println("--- Consultar Saldo ---");

        Account account = searchAccount();
        if (account == null) return;
        System.out.println("\n" + account);
    }

    private static void listAccounts() {
        System.out.println("\n--- Todas as Contas ---");

        if (accounts.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada ainda.");
            return;
        }

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i));
        }
    }

    // Métodos auxiliares
    private static Account searchAccount() {
        if (accounts.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada ainda.");
            return null;
        }
        System.out.print("Digite o número da conta: ");
        String numberAccount = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getNumber().equals(numberAccount)) {
                return account;
            }
        }
        System.out.println("Conta não encontrada!");
        return null;
    }

    private static void pause() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
