<script lang="ts">
  import { goto } from '$app/navigation'; // Para navegação no SvelteKit
  import homeIcon from '@icons/home.svg';
  import personIcon from '@icons/person.svg';
  import bagIcon from '@icons/bag.svg';
  import settingsIcon from '@icons/settings.svg';

  const icons: { icon: string, href: string }[] = [
    { icon: homeIcon, href: "/home" },
    { icon: personIcon, href: "/home/customers" },
    { icon: bagIcon, href: "/home/transactions" },
    { icon: settingsIcon, href: "/home/settings" }
  ];

  let activeIndex = 0;
  let isAnimating = false; // Controle para a animação

  // Função para controlar a navegação com animação
  function navigateWithAnimation(index: number, href: string) {
    if (isAnimating) return; // Se já está animando, ignora o clique

    isAnimating = true; // Inicia a animação
    activeIndex = index; // Muda para o ícone selecionado imediatamente

    // A animação será feita, e ao terminar, vamos navegar
    setTimeout(() => {
      goto(href); // Navegação após o tempo da animação
    }, 500); // O tempo precisa coincidir com a duração da animação
  }

  // Função chamada quando a transição do ícone termina
  function onTransitionEnd() {
    isAnimating = false; // Permite novas animações
  }
</script>

<div class="navigation">
  <ul>
    {#each icons as icon, i}
      <li class:active={activeIndex === i}
          on:click={() => navigateWithAnimation(i, icon.href)}
          class="icon-item"
          on:transitionend={onTransitionEnd}> <!-- Espera pela animação -->
        <a href="#">
          <span class="icon">
            <img src={icon.icon} class="svg-icon" alt="icon" />
          </span>
        </a>
      </li>
    {/each}
    <div class="indicator" style="transform: translateX(calc(80px * {activeIndex} - 115px));">
      <span></span>
    </div>
  </ul>
</div>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  :root {
    --clr-background: #F9F2ED;
    --clr-primary: #ff0000;
    --clr-secondary: #EDD6C8;
    --clr-dark: #313131;
    --clr-light: #E3E3E3;
  }

  body {
    display: flex;
    justify-content: center;
    align-items: end;
    min-height: 100vh;
    background: var(--clr-background);
  }

  .navigation {
    width: 105%;
    height: 70px;
    background: var(--clr-primary);
    display: flex;
    justify-content: center;
    align-items: center;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
  }

  .navigation ul {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
  }

  .navigation ul li {
    position: relative;
    list-style: none;
    width: 80px;
    height: 70px;
    z-index: 1;
    cursor: pointer;
  }

  .navigation ul li a {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
  }

  .navigation ul li a .icon {
    position: relative;
    display: block;
    font-size: 2em;
    color: var(--clr-secondary);
    opacity: 0.85;
    transition: opacity 0.3s, transform 0.3s;
  }

  .navigation ul li.active a .icon {
    opacity: 1;
    transform: translateY(-7px);
    filter: brightness(0) saturate(100%) invert(12%) sepia(82%) saturate(7499%) hue-rotate(1deg) brightness(96%) contrast(116%);
    transition: opacity 0.3s, transform 0.3s;
  }

  .svg-icon {
    width: 30px;
    height: 60px;
    margin-left: 5px; /* Move 3px para a direita */
    transition: transform 0.3s ease;
  }

  .indicator {
    position: absolute;
    top: -10px;
    width: 70px;
    height: 70px;
    background: var(--clr-background);
    border-bottom-left-radius: 35px;
    border-bottom-right-radius: 35px;
    border: 6px solid var(--clr-background);
    cursor: pointer;
    transition: transform 0.5s ease;
  }

  .indicator::before {
    content: "";
    position: absolute;
    top: 4px;
    left: -25.75px;
    width: 20px;
    height: 20px;
    background: transparent;
    border-top-right-radius: 20px;
    box-shadow: 4px -6px 0 2px var(--clr-background);
  }

  .indicator::after {
    content: "";
    position: absolute;
    top: 4px;
    right: -25.75px;
    width: 20px;
    height: 20px;
    background: transparent;
    border-top-left-radius: 20px;
    box-shadow: -4px -6px 0 2px var(--clr-background);
  }

  .indicator span {
    position: absolute;
    left: -3px;
    width: 60px;
    height: 60px;
    border: 4px solid var(--clr-primary);
    border-radius: 50%;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
    transform: scale(0.85);
  }
</style>
