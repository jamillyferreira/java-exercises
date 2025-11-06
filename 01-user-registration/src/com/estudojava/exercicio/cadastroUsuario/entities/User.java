package com.estudojava.exercicio.cadastroUsuario.entities;

import com.estudojava.exercicio.cadastroUsuario.exceptions.InvalidEmailException;
import com.estudojava.exercicio.cadastroUsuario.exceptions.InvalidPasswordException;

public class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) throws InvalidEmailException, InvalidPasswordException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        if (!email.contains("@")) {
            throw new InvalidEmailException("Email inválido: falta '@'.");
        }
        if (password.length() < 8) {
            throw new InvalidPasswordException("Senha fraca: deve ter pelo menos 8 caracteres.");
        }

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Usuário: " + name + " | Email: " + email;
    }
}
