package com.estudojava.exercicios.validadorIdade.entities;

public class Pessoa {
    private String name;
    private int age;

    public Pessoa() {
    }

    public Pessoa(String name, int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Idade inválida: " + age);
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " - " + age + " anos";
    }
}
