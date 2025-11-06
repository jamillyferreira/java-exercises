# Cadastro de Usuário Simples

## Descrição

Este exercício foi desenvolvido com o objetivo de praticar **tratamento de exceções**, validação de dados de entrada e criação de classes personalizadas de exceção em Java.
O programa simula um sistema simples de cadastro de usuário, solicitando nome, email e senha.
Durante o processo, ele valida as informações inseridas e impede o cadastro se algum dado estiver incorreto.

## Conceitos 
- **Encapsulamento e orientação a objetos**:
    
    Utilização da classe User para representar um usuário, com atributos e validações.


- **Tratamento de exceções**:

    Uso de try-catch para capturar erros de validação e permitir que o usuário tente novamente.


- **Criação de exceções personalizadas**:
    
    Classes InvalidEmailException e InvalidPasswordException, que tratam erros específicos de validação.


- **Scanner e entrada de dados pelo console**:
    
    Leitura de informações diretamente pelo terminal.


- **Loop de repetição com condição de erro**: 

    O programa só finaliza o cadastro quando todos os dados são válidos.