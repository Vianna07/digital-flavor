<script lang="ts">
  import homeIcon from '@icons/home.svg';
  import personIcon from '@icons/person.svg';
  import bagIcon from '@icons/bag.svg';
  import settingsIcon from '@icons/settings.svg';
  const icons = [homeIcon, personIcon, bagIcon, settingsIcon];
  let activeIndex = 0;

  function setActive(index: number) {
    activeIndex = index;
  }
</script>

<div class="navigation">
  <ul>
    {#each icons as icon, i}
      <li class:active={activeIndex === i}>
        <a href="#" on:click={() => setActive(i)}>
          <span class="icon">
            <img src={icon} class="svg-icon" alt="icon" />
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
    transition: 0.3s;
  }

  .navigation ul li.active a .icon {
  opacity: 1;
  transform: translateY(-7px);
  filter: brightness(0) saturate(100%) invert(12%) sepia(82%) saturate(7499%) hue-rotate(1deg) brightness(96%) contrast(116%);
  transition: transform 0.3s ease, filter 0.3s ease;
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