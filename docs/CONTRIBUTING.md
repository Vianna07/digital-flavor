# Contribuindo para o Digital Flavor

Obrigado por considerar contribuir para o **Digital Flavor**! Valorizamos suas contribuições e ideias para melhorar o nosso projeto de gestão de cantinas.

## Como Contribuir

### Relatando Problemas

Se você encontrar um bug ou tiver uma sugestão, por favor, abra uma **issue** no GitHub. Antes de abrir uma nova issue, verifique se já existe uma issue aberta para evitar duplicações. Quando relatar um problema, forneça o máximo de detalhes possível para que possamos reproduzir e resolver o problema de maneira eficiente.

### Contribuindo com Código

1. **Fork** o repositório.
2. **Clone** o repositório forkado para sua máquina local:
    ```sh
    git clone https://github.com/SEU_USUARIO/digital-flavor.git
    ```
3. Crie uma nova branch para sua alteração:
    ```sh
    git checkout -b minha-alteracao
    ```
4. Faça as alterações necessárias e **commit**:
    ```sh
    git commit -m "feat: adiciona nova alteração"
    ```
5. **Push** para a branch:
    ```sh
    git push origin minha-alteracao
    ```
6. Abra um **Pull Request** no GitHub.

### Convenção de Commits

Para manter a consistência no histórico de commits, siga as [diretrizes de commit do projeto](./CONVENTIONAL_COMMITS.md).

#### Exemplos

- `feat: adiciona funcionalidade de pedido online`
- `fix: corrige erro no cálculo do total do pedido`
- `docs: atualiza README com instruções de instalação`
- `style: remove espaçamento desnecessário no CSS`
- `refactor: reorganiza a estrutura dos componentes`
- `test: adiciona testes para funcionalidade de pagamento`
- `chore: atualiza dependências do projeto`

### Boas Práticas de Código

- **Linting**: Verifique se seu código está de acordo com as regras de linting do projeto (Ex: ESLint e Prettier para o frontend).
- **Testes**: Adicione testes sempre que possível para validar suas mudanças.
- **Documentação**: Atualize a documentação conforme necessário, incluindo o [README.md](../README.md), [BACKLOG.md](./BACKLOG.md) e [CHANGELOG.md](./CHANGELOG.md).

## Ambiente de Desenvolvimento

Para configurar o ambiente de desenvolvimento, siga as instruções no [README.md](../README.md).

## Código de Conduta

Ao contribuir para este projeto, você concorda em seguir nosso [Código de Conduta](../CODE_OF_CONDUCT.md).

## Licença

Ao contribuir para o projeto, você concorda que suas contribuições serão licenciadas sob a [Licença MIT](../LICENSE).
