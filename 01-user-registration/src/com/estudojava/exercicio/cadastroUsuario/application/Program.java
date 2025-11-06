package com.estudojava.exercicio.cadastroUsuario.application;

import com.estudojava.exercicio.cadastroUsuario.entities.User;
import com.estudojava.exercicio.cadastroUsuario.exceptions.InvalidEmailException;
import com.estudojava.exercicio.cadastroUsuario.exceptions.InvalidPasswordException;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        System.out.println("=== CADASTRO DE USUÁRIO ===");

//        try-with=resources garante que o Scanner seja fechado automanticamente
        try (Scanner scanner = new Scanner(System.in)) {
//            Começa como null pra sabermos quando o cadastro foi criado com sucesso
            User user = null;

//            Enquanto o usuário for nulo(vazio), o programa continua pedindo os dados
//            Isso acontece se ocorrer algum erro de validação
            while (user == null) {
                try {
                    System.out.print("Digite o seu nome: ");
                    String name = scanner.nextLine();

//                  Pede o email -> a validação acontece dentro da classe User
                    System.out.print("Digite um email: ");
                    String email = scanner.nextLine();

//                  Pede a senha -> também será validada dentro da classe User
                    System.out.print("Digite uma senha: ");
                    String password = scanner.nextLine();

//                  Cria o objeto User se forem validos
                    user = new User(name, email, password);

//                  Captura as exceções personalizadas de email e senha inválidos,
//                  E possíveis IllegalArgumentException (ex: nome vazio)
                } catch (InvalidEmailException | InvalidPasswordException | IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                    System.out.println("Tente novamente.");
                }
            }
            System.out.println("\nCadastro criado com sucesso!");
            System.out.println(user);

        }

    }
}
