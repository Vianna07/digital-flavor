<script lang="ts">
	import type { ProductCardProps } from "$lib/types";

  let {
      name,
      description,
      price,
      imageUrl,
      onclick = () => {},
  }: ProductCardProps = $props();
</script>

<div class="product-card ">
  <img src={imageUrl} alt={name} class="product-card__image w-32 h-28 rounded-lg" />
  <div class="product-card__info">
    <h3 class="product-card__name">{name}</h3>
    <p class="product-card__short-description">{description.slice(0, 20)}</p>
  </div>

  <div class="product-card__purchase">
      <h1 class="product-card__price">R$ {price.toFixed(2)}</h1>
      <button
        class="product-card__add-to-order"
        {onclick}
      >
        +
      </button>
  </div>
</div>

<style lang="postcss">
  .product-card {
    @apply flex flex-col items-center rounded-lg p-3 gap-3 w-157px h-231px shadow-xl bg-light;

    &__info {
      @apply w-full;
    }
    &__name {
      text-base font-bold text-left text-contrast
    }
    &__short-description {
      text-xs text-secondary text-left mt-0
    }
    &__purchase {
      flex items-center justify-between w-full
    }
    &__price {
      text-base font-bold text-contrast
    }
    &__add-to-order {
      flex items-center justify-center bg-primary text-light border-none rounded-[30%] w-8 h-8 text-lg cursor-pointer hover:bg-primary/90 transition-colors
    }
  }

</style>
