# Frontend

## Ambiente de desenvolvimento

1. Instale o pnpm:
   `npm i -g pnpm`
2. Instale as dependências:
   `pnpm i`
3. Inicie o ambiente de desenvolvimento:
   `pnpm dev`

## Estrutura de CSS

Para manter o código CSS organizado e fácil de manter, utilizamos uma combinação de SMACSS, BEM e namespaces.

### BEM (Block, Element, Modifier)

A nomenclatura BEM é usada para facilitar a legibilidade e manutenção:

- **Block**: Unidade independente (ex.: order, menu).
- **Element**: Parte de um bloco com função específica (ex.: order**details, menu**item).
- **Modifier**: Variação ou estado (ex.: order--pending, menu\_\_item--out of stock).
