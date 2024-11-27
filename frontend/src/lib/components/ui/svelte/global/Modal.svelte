<script lang="ts">
  import closeIcon from "@icons/close.svg"
	import type { ModalProps } from "$lib/types";

  let { children, isOpen, title }: ModalProps = $props();

  const closeModal = () => (isOpen = false);
</script>

{#if isOpen}
  <div class="screen">
    <div class="screen__modal">
      <header>
        <h1>{title}</h1>

        <button onclick={closeModal}>
          <img src={closeIcon} alt="closeIcon">
        </button>
      </header>

      {@render children?.()}
    </div>
  </div>
{/if}

<style class="postcss">
  .screen {
    @apply fixed top-0 left-0 w-screen h-screen bg-black bg-opacity-50 flex items-center justify-center z-50;

    &__modal {
      @apply bg-white p-6 rounded-lg shadow-lg w-80 min-h-56 flex flex-col items-center gap-5;

      header {
        @apply flex justify-between;

        h1 {
          @apply text-xl font-bold;
        }
      }
    }
  }
</style>
