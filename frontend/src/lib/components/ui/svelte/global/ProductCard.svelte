<script lang="ts">
	import type { ProductCardProps } from "$lib/types";

  let {
      id,
      name,
      shortDescription,
      price,
      imageUrl,
      onclick = () => {},
  }: ProductCardProps = $props();
</script>

<div class="product-card">
  <a href={`/home/details/product/${id}`}>
    <img src={imageUrl} alt={name} />

    <div class="product-card__info">
      <h3 class="product-card__info__name">{name}</h3>
      <p class="product-card__info__short-description">{shortDescription}</p>
    </div>

    <div class="product-card__purchase">
        <h1 class="product-card__purchase__price">R$ {price.toFixed(2)}</h1>
        <button
          class="product-card__purchase__add-to-order"
          {onclick}
        >
          +
        </button>
    </div>
  </a>
</div>

<style lang="postcss">
  .product-card {
    a {
      @apply flex flex-col items-center rounded-lg p-3 gap-10 w-40 h-60 shadow-xl border-2 border-secondary-300 cursor-pointer;

      transition: 200ms all ease-in-out;

      &:hover {
        @apply scale-105;
      }

    }

    img {
      @apply w-32 h-28 rounded-lg;
    }

    &__info {
      @apply w-full;

      &__name {
        @apply text-base font-bold text-left text-contrast;
      }
      &__short-description {
        @apply text-xs text-secondary text-left mt-0;
      }
    }

    &__purchase {
      @apply flex items-center justify-between w-full;

      &__price {
        @apply text-base font-bold text-contrast;
      }

      &__add-to-order {
        @apply flex items-center justify-center bg-primary text-light border-none rounded-xl w-8 h-8 text-lg cursor-pointer transition-colors;

        &:hover {
          @apply bg-primary-700 ;
        }
      }
    }
  }
</style>
