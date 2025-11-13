# Sistema Bancário (Console App)
O sistema simula operações básicas de um banco, como criação de contas, depósitos, saques e transferências entre usuários.

## Sobre o projeto
Este projeto foi desenvolvido como exercício prático para dominar conceitos fundamentais de Java:

- Programação Orientada a Objetos
- Exceções customizadas
- Try-catch-finally
- Validação de dados
- Composição de classes
- Encapsulamento

## Funcionalidades

- Criar conta bancária
- Realizar depósito
- Efetuar saque com validação de saldo
- Consultar saldo
- Transferir valores entre contas
- Listar todas as contas cadastradas
- Validações completas de entrada e erros tratados por exceções

## Conceitos praticados
### Encapsulamento e composição

```java
// Encapsulamento
public class Account {
    private double balance; // Não pode ser acessado diretamente
    
    public double getBalance() {
        return balance; // Acesso controlado
    }
}

// Composição
public class Account {
    private User holder; // Conta TEM UM usuário

    public Account(String number, User holder) {
        this.holder = holder;
    }
}
```

### Exceções Customizadas

- `InvalidUserDataException`
- `InvalidAccountDataException`
- `InvalidAmountException`
- `InsufficientBalanceException`

### Outros conceitos
- Validação de entrada com expressões regulares
- Tratamento de erros com `try-catch`
- Reuso e organização de código (métodos auxiliares como `searchAccount()` e `pause()`)
- Separação de pacotes (entities, exceptions, application)

## O que aprendi

- Criar classes com atributos privados
- Implementar construtores com validação
- Criar e lançar exceções customizadas
- Usar `try-catch` para tratamento de erros
- Implementar composição entre classes
- Validar dados de entrada do usuário
- Trabalhar com `Scanner` e entrada de dados
- Usar static em métodos utilitários
- Manipular coleções com `ArrayList`
