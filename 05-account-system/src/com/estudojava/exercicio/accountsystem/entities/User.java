package com.estudojava.exercicio.accountsystem.entities;

import com.estudojava.exercicio.accountsystem.exceptions.InvalidUserDataException;

public class User {
    private String name;
    private String id;

    public User(String name, String id) throws InvalidUserDataException {
        validateName(name);
        validateId(id);
        this.name = name;
        this.id = id;
    }

    public void validateName(String name) throws InvalidUserDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidUserDataException("O nome não pode está vazio.");
        }
        if (!name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new InvalidUserDataException("O nome deve conter apenas letras e espaços.");
        }
    }

    public void validateId(String id) throws InvalidUserDataException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidUserDataException("O ID não pode está vazio.");
        }
        if (!id.matches("\\d+")) {
            throw new InvalidUserDataException("O ID deve conter apenas números.");
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    // Atualiza nome do usuário sem quebrar a validação
    public void setName(String newName) throws InvalidUserDataException {
        validateName(newName);
        this.name = newName.trim();
    }

    @Override
    public String toString() {
        return "Usuário: " + name + " (ID: " + id + ")";
    }
}
