
# Digital Flavor

## Descrição
**Digital Flavor** é um aplicativo desenvolvido para gerenciar a cantina escolar de forma prática e eficiente. Ele permite que os alunos façam pedidos online, acompanhem o cardápio da semana e façam pagamentos diretamente pelo aplicativo, proporcionando uma experiência rápida e integrada.

## Funcionalidades
- **Pedidos Online**: Os alunos podem fazer pedidos de alimentos e bebidas diretamente pelo aplicativo.
- **Cardápio Semanal**: Visualização do cardápio da cantina atualizado semanalmente.
- **Pagamentos Integrados**: Suporte para pagamento online via cartão de crédito, débito ou Pix.
- **Histórico de Pedidos**: Acompanhamento de pedidos realizados.
- **Notificações**: Notificações em tempo real sobre o status do pedido.

## Tecnologias Utilizadas
- **Controle de Versão**: Git
- **Frontend**: React
- **Linguagem**: TypeScript, TSX
- **CSS**: PostCSS, TailwindCSS
- **Build Tool**: Vite
- **Testes**: Vitest, Testing Library
- **Linting**: ESLint, Prettier
- **Contêineres**: Docker
- **Scripts Automatizados**: Make
- **Servidor Web**: Nginx

## Estrutura de CSS
Para manter o código CSS organizado e fácil de manter, utilizamos uma combinação de SMACSS, BEM e namespaces.

### SMACSS (Scalable and Modular Architecture for CSS)
Nosso CSS está organizado em:
- **Base**: Estilos globais e padrões básicos.
- **Layout**: Estilos que definem a estrutura da página.
- **Module**: Estilos para componentes como o cardápio ou a página de pedidos.
- **State**: Estilos para diferentes estados de um módulo ou layout.
- **Theme**: Estilos visuais para diferentes temas.

### BEM (Block, Element, Modifier)
A nomenclatura BEM é usada para facilitar a legibilidade e manutenção:
- **Block**: Unidade independente (ex.: pedido, cardápio).
- **Element**: Parte de um bloco com função específica (ex.: pedido__detalhes, cardapio__item).
- **Modifier**: Variação ou estado (ex.: pedido--pendente, cardapio__item--esgotado).

### Namespaces no CSS
Utilizamos namespaces para categorizar os seletores:
- **c-**: Componentes (`c-pedido`, `c-cardapio`).
- **l-**: Layouts (`l-header`, `l-footer`).
- **u-**: Utilitários (`u-hidden`, `u-clearfix`).
- **js-**: Classes utilizadas por scripts (`js-toggle`, `js-modal`).

## Instalação e Ambiente de Desenvolvimento

### Arquivos .env
Para configurar o ambiente de desenvolvimento, crie os arquivos de configuração:
- **.env.development.local**: Configurações para o ambiente de desenvolvimento.
- **.env.production.local**: Configurações para o ambiente de produção.

Crie esses arquivos a partir do `.env.example` incluído no repositório.

### Instalação Local
1. Clone o repositório:  
   `git clone https://github.com/Vianna07/digital-flavor.git`
2. Entre no diretório do projeto:  
   `cd digital-flavor`
3. Instale as dependências:  
   `pnpm install`
4. Inicie o ambiente de desenvolvimento:  
   `pnpm dev`

### Instalação com Docker
Para executar o projeto usando Docker:
1. Certifique-se de que o Docker está instalado:  
   Consulte [aqui](https://docs.docker.com/get-docker/).
2. Instale o Make:  
   No Windows, use `choco install make`.
3. Inicie os contêineres Docker:  
   `make dev`
