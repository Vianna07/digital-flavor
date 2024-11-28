<script lang="ts">
  import { goto } from '$app/navigation';
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

  function navigateWithAnimation(index: number, href: string) {
    activeIndex = index;
  }
</script>

<footer>
  <nav>
    <ul>
      {#each icons as {icon, href}, i}
        <li class:active={activeIndex === i}
            class="icon-item">
          <a href={href} on:click={() => navigateWithAnimation(i, href)}>
            <span class="icon">
              <img src={icon} alt="icon" />
            </span>
          </a>
        </li>
      {/each}
      <div class="indicator" style="transform: translateX(calc(80px * {activeIndex} - 115px));">
        <span></span>
      </div>
    </ul>
  </nav>
</footer>

<style lang="postcss">
  footer, nav {
    @apply w-full h-[4.375rem] bg-primary flex justify-center items-center rounded-xl;
  }

  nav {
    ul {
      @apply relative flex justify-center items-center w-full;

      li {
        @apply relative list-none w-20 h-[4.375rem] z-10 cursor-pointer;

        a {
          @apply relative flex justify-center items-center w-full;

          .icon {
            @apply relative block text-[2em] text-light opacity-85;

            transition: opacity 0.3s, transform 0.3s;

            img {
              @apply w-[1.875rem] h-[3.75rem] ml-[0.3125rem];

              transition: transform 0.3s ease;
            }
          }
        }

        &.active {
          a {
            .icon {
              @apply opacity-100 -translate-y-[0.4375rem];

              filter: brightness(0) invert(12%) sepia(82%) saturate(50);
              transition: opacity 0.3s, transform 0.3s;
            }
          }
        }
      }

      .indicator {
        @apply absolute -top-[0.625rem] w-[4.375rem] h-[4.375rem] bg-light rounded-bl-[2.1875rem] rounded-br-[2.1875rem] border-[6px] border-solid border-light cursor-pointer;

        transition: transform 0.5s ease;

        &::before, &::after {
          @apply absolute top-1 w-5 h-5 bg-transparent;

          content: "";
        }

        &::before {
          @apply -left-[1.609375rem]  rounded-tr-[1.25rem];

          box-shadow: 4px -6px 0 2px theme('colors.light');
        }

        &::after {
          @apply -right-[1.609375rem]  rounded-tl-[1.25rem];

          box-shadow: -4px -6px 0 2px theme('colors.light');
        }

        span {
          @apply absolute w-[3.75rem] h-[3.75rem] rounded-[50%] scale-[0.85] border-4 border-solid border-primary -left-[0.1875rem];

          box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
        }
      }
    }
  }
</style>
