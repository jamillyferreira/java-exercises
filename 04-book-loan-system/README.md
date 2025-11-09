# Sistema de Empréstimo de Livros

Exercício de estudo sobre exceções personalizadas, encapsulamento e validação de regras de negócio em Java
(sem interface gráfica).

## Conceitos Praticados
### Exceções Checked
- `BookNotFoundException`, `BookUnavailableException`, `LoanLimitsException`
- Força tratamento explícito pelo chamador
- Útil quando erro é esperado e recuperável

### Encapsulamento e Responsabilidades
- **Library**: gerencia acervo, não conhece regras de usuário
- **User**: valida próprio limite, não expõe lista interna diretamente
- Cada classe conhece e impõe suas próprias regras

### Cópia Defensiva

```java
import java.util.List;

public List getBorrowedBooks() {
    return new ArrayList<>(borrowedBooks); // protege estado interno
}
```
Previne: `user.getBorrowedBooks().clear()` // não afeta o original

### Constantes de Classe
```java
private static final int LOAN_LIMIT = 3;
```
- `static`: compartilhada por todas instâncias
- `final`: valor imutável
- Centraliza regra de negócio

## Cenários Testados
1. Empréstimo normal
2. Livro indisponível (já emprestado)
3. Múltiplos empréstimos
4. Limite excedido (max 3 livros)
5. ISBN não encontrado
6. Devolução
7. Re-empréstimo após devolução

## Aprendizados
- Exceções como controle de fluxo vs validação
- Reforcei a importância de inicializar coleções no construtor
- Usei guard clauses para validar antes de alterar o estado do objeto
- Percebi como cada classe deve proteger seu próprio estado (encapsulamento real)