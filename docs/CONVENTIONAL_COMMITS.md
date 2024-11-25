# Convenção de Commits

Para garantir consistência e clareza no histórico de commits, siga as diretrizes abaixo:

## Formato do Commit
O formato e os tipos de commit seguem a convenção padrão.

### Tipos de Commit

- **feat**: uma nova funcionalidade.

- **fix**: correção de bugs.

- **docs**: atualizações ou mudanças na documentação.

- **style**: alterações que não afetam o comportamento do código (ex.: formatação, espaços em branco).

- **refactor**: alterações no código que não adicionam funcionalidades ou corrigem bugs.

- **test**: adição ou modificação de testes.

- **chore**: tarefas administrativas, como atualizações de dependências e ajustes no build.

### Exemplos
- `feat: adiciona funcionalidade de login para funcionários da cantina`
- `fix: corrige erro de validação no formulário de pedido`
- `docs: atualiza README com instruções de instalação`

### Estrutura do Commit
```plaintext
<header />
<LINHA_EM_BRANCO />
<body> (opcional)
<LINHA_EM_BRANCO />
<footer> (opcional)
```

- **Header**: `<type>: <short summary>`
    - **type**: Define o tipo da mudança (feat, fix, etc.).
    - **short summary**: Um resumo breve da alteração. Escreva no tempo presente, sem ponto final e não capitalizado.

- **Body** (opcional):
    - Descrição detalhada da mudança.
    - Explique o motivo da alteração e como foi implementada.
    - Escreva de forma clara e objetiva, em parágrafos curtos.

- **Footer** (opcional):
    - Notas adicionais, como *breaking changes* ou referência a issues fechadas.
    - **BREAKING CHANGE**: Descrição de alterações que quebram a compatibilidade anterior.
    - **Closes**: Indica as issues resolvidas pela mudança.

Seguir essas diretrizes ajudará a manter um histórico de commits limpo e organizado, facilitando a manutenção e a colaboração no projeto.
