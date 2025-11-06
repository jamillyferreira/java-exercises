# Validador de Idade

## Descrição
Programa simples em java que valida uma idade informada pelo usuário.
Se a idade for menor que 0 ou maior que 120, o sistema lança uma `IllegalArgumentException`.

## Conceitos praticados
- Criação de classes e construtores
- Encapsulamento (`private`, atributos e `get/set`)
- Lançamento de exceções (`throw new IllegalArgumentExcepiton`)
- Estrutura `try/catch` para tratar exceções
- Uso do `Scanner` para entradas de dados

## Ponto de atenção
Ainda estudando:
- Diferença entre `RuntimeException` (unchecked) e exceções verificadas (checked)

## Exemplo de execução
````terminaloutput
=== TESTANDO VALIDADOR DE IDADE ===

Digite o seu nome: Jamilly
Digite sua idade: 25

Pessoa criada com sucesso:
Jamilly - 25 anos
````
