# Exercícios de Java

Este repositório contém uma coleção de exercícios desenvolvidos durante meus estudos de Java, com foco em reforçar conceitos fundamentais da linguagem e boas práticas de programação.

---

## O que já fiz

1. **[user-registration](./01-user-registration/)** - Sistema de cadastro com validações
    - Exceções customizadas
    - Validação de email e senha

2. **[age-validator](./02-age-validator/)** - Validador de idade
    - IllegalArgumentException
    - Validações numéricas

3. **[hotel-reservation](./03-hotel-reservation-system/)** - Sistema de reservas
    - LocalDate e DateTimeFormatter
    - Validações de datas
    - Múltiplas exceções
  
4. **[book-loan-system](./04-book-loan-system)** - Sistema de Empréstimo de Livros
    - Exceções checked personalizadas (BookNotFoundException, BookUnavailableException, LoanLimitsException)
    - Encapsulamento e responsabilidades claras entre classes
    - Cópia defensiva para proteger estado interno
    - Constantes de classe para regras de negócio
    - Limite de 3 empréstimos por usuário
    - Validação de ISBN e disponibilidade
      
5. **[account-system](./05-account-system)** - Sistema Bancário (Console App)
    - Exceções customizadas para operações financeiras
    - Composição entre classes Account e User
    - Validação com expressões regulares
    - Operações: depósito, saque, transferência, consulta de saldo
    - Tratamento completo de erros com try-catch
    - Separação em pacotes (entities, exceptions, application)
---

## Tecnologias e Conceitos
- Java 21
- Programação Orientada a Objetos (OOP)
- Tratamento de Exceções (`try`, `catch`, `finally`)
- Exceções customizadas
- Manipulação de Datas (LocalDate, DateTimeFormatter)
- Validação de dados
- Estruturação e organização de código
- Boas práticas de desenvolvimento

---

## Como usar
```bash
# Clone o repositório
git clone https://github.com/seu-usuario/java-exercises.git

# Entre na pasta do exercício desejado
cd java-exercises/01-user-registration

# Compile e execute
javac *.java
java Program
```
---

## Conceitos Praticados

- Exceções customizadas
- Try-catch-finally
- Validação de dados
- LocalDate
- Encapsulamento
- Boas práticas de POO

---

_Feito com dedicação durante meus estudos de Java ☕_
