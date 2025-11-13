package com.estudojava.exercicio.accountsystem.entities;

import com.estudojava.exercicio.accountsystem.exceptions.InsufficientBalanceException;
import com.estudojava.exercicio.accountsystem.exceptions.InvalidAccountDataException;
import com.estudojava.exercicio.accountsystem.exceptions.InvalidAmountException;

public class Account {
    private String number;

    // Associação com a classe User (composicao)
    // Uma conta sempre pertence a um titular (User)
    private User holder;
    private double balance;

    public Account(String number, User holder) throws InvalidAccountDataException {
        validateNumber(number);
        validateHolder(holder); // Garante que existe um titular associado
        this.number = number;
        this.holder = holder;
    }

    public String getNumber() {
        return number;
    }

    public User getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }

    public void validateNumber(String number) throws InvalidAccountDataException {
        if (number == null || number.trim().isEmpty()) {
            throw new InvalidAccountDataException("O número da conta não pode está vazio.");
        }
        if (!number.matches("[0-9\\-]+")) {
            throw new InvalidAccountDataException("O número da conta deve conter apenas dígitos ou hífens.");
        }
    }

    // Valida a existencia de um titular
    // aqui nao valida os dados do User, a classe User é responsavel por isso
    public void validateHolder(User holder) throws InvalidAccountDataException {
        if (holder == null) {
            throw new InvalidAccountDataException("A conta deve ter um proprietário válido.");
        }
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("O valor do depósito deve ser maior que zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("O valor do saque deve ser maior que zero.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Saldo insuficiente para saque.");
        }
        balance -= amount;

    }

    public void transfer(Account target, double amount)
            throws InvalidAccountDataException, InvalidAmountException, InsufficientBalanceException {
        if (target == null) {
            throw new InvalidAccountDataException("A conta de destino não pode ser vazio.");
        }
        if (target.equals(this)) {
            throw new InvalidAccountDataException("Não é possível transferir para a mesma conta.");
        }
        this.withdraw(amount); // Tira o dinheiro DESTA conta
        target.deposit(amount); // Coloca dinheiro na conta DESTINO
    }

    /**
     * Altera o titular da conta
     * Mesmo que o User já seja validado,
     * ainda verificamos se não é nulo para evitar estado inconistente.
     */
    public void setHolder(User newHolder) throws InvalidAccountDataException {
        if (newHolder == null) {
            throw new InvalidAccountDataException("A conta precisa ter um dono válido.");
        }
        this.holder = newHolder;
    }

    @Override
    public String toString() {
        return "Conta: " + number
                + " | Titular: " + holder.getName()
                + " | Saldo: R$" + String.format("%.2f", balance);
    }
}
