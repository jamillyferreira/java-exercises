package com.estudojava.exercicio.hotelreservation.application;

import com.estudojava.exercicio.hotelreservation.entities.Reserve;
import com.estudojava.exercicio.hotelreservation.exceptions.InvalidDateException;
import com.estudojava.exercicio.hotelreservation.exceptions.InvalidReserveException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Program {
    // Formato padrão que o sistema vai usar para ler e mostrar as datas
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("\n=== SISTEMA DE RESERVA DO HOTEL ===");

        // uso try-with-resources pra garantir que o Scanner será fechado automaticamente
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nome do hóspede: ");
            String guestName = scanner.nextLine();

            // Variáveis de data começam nulas até o usuário digitar algo válido
            LocalDate checkIn = null;
            while (checkIn == null) {
                try {
                    System.out.print("Data de check-in (dd/MM/yyyy): ");
                    String dateStr = scanner.nextLine();
                    // converte o texto em uma data
                    checkIn = LocalDate.parse(dateStr, FMT);
                } catch (DateTimeParseException e) {
                    // se o formato estiver errado, mostra mensagem e repete o processo
                    System.out.println("Data inválida. Use o formato dd/MM/yyyy");
                    System.out.println("Tente novamente.\n");
                }
            }

            LocalDate checkOut = null;
            while (checkOut == null) {
                try {
                    System.out.print("Data de check-out (dd/MM/yyyy): ");
                    String dateStr = scanner.nextLine();
                    checkOut = LocalDate.parse(dateStr, FMT);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida. Use o formato dd/MM/yyyy");
                    System.out.println("Tente novamente.\n");
                }
            }

            // variável que vai guardar a reserva
            Reserve reserve = null;
            while (reserve == null) {
                try {
                    // cria uma reserva com dados informados
                    reserve = new Reserve(guestName, checkIn, checkOut);
                    System.out.println("\nReserva criada com sucesso!");
                    System.out.println(reserve);
                } catch (InvalidDateException | InvalidReserveException e) {
                    // caso a reserva tenha datas inválidas (ex: check-out antes do check-in)
                    System.out.println("\nERRO: " + e.getMessage());
                    System.out.println("Digite novas datas abaixo:");

                    // repete a leitura de novas data válidas
                    checkIn = null;
                    while (checkIn == null) {
                        try {
                            System.out.print("Nova data de check-in (dd/MM/yyyy): ");
                            String dateStr = scanner.nextLine();
                            checkIn = LocalDate.parse(dateStr, FMT);
                        } catch (DateTimeParseException e2) {
                            System.out.println("Data inválida. Use o formato dd/MM/yyyy");
                        }
                    }

                    checkOut = null;
                    while (checkOut == null) {
                        try {
                            System.out.print("Nova data de check-out (dd/MM/yyyy): ");
                            String dateStr = scanner.nextLine();
                            checkOut = LocalDate.parse(dateStr, FMT);
                        } catch (DateTimeParseException e2) {
                            System.out.println("Data inválida. Use o formato dd/MM/yyyy");

                        }
                    }
                }
            }

            // depois de criar a reserva, pergunta se o usuário quer atualizar as datas
            System.out.print("\nDeseja atualizar as datas de reserva? (s/n): ");
            String response = scanner.next();

            if (response.equalsIgnoreCase("s")) {
                boolean updateWithSuccess = false;

                while (!updateWithSuccess) {
                    try {

                        // pede novas datas de atualização
                        LocalDate newCheckIn = null;
                        while (newCheckIn == null) {
                            try {
                                System.out.print("Nova data de check-in (dd/MM/yyyy): ");
                                String dateStr = scanner.next();
                                newCheckIn = LocalDate.parse(dateStr, FMT);
                            } catch (DateTimeParseException e) {
                                System.out.println("Data inválida. Use o formato dd/MM/yyyy");
                            }
                        }

                        LocalDate newCheckOut = null;
                        while (newCheckOut == null) {
                            try {
                                System.out.print("Nova data de check-out (dd/MM/yyyy): ");
                                String dateStr = scanner.next();
                                newCheckOut = LocalDate.parse(dateStr, FMT);
                            } catch (DateTimeParseException e) {
                                System.out.println("Data inválida. Use o formato dd/MM/yyy");
                            }
                        }

                        // atualiza as datas dentro do objeto reserva
                        reserve.updateDates(newCheckIn, newCheckOut);
                        System.out.println("\nData atualizadas com sucesso!");
                        System.out.println(reserve);
                        updateWithSuccess = true;

                    } catch (InvalidDateException | InvalidReserveException e) {
                        System.out.println("ERRO: " + e.getMessage());
                        System.out.println("Tente novamente.\n");
                    }
                }
            }

            System.out.println("\nObrigado por usar nosso sistema!");

        } catch (Exception e) {
            // caso aconteça algum erro inesperado (fora das exceções previstas)
            System.out.println("Erro inesperado: " + e.getMessage());
        }

    }
}
