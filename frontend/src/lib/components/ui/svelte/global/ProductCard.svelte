<script lang="ts">
	import type { ProductCardProps } from '$lib/types';

	let {
		id,
		name,
		shortDescription,
		price,
		imageUrl,
		onclick = () => {}
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
			<button class="product-card__purchase__add-to-order" {onclick}> + </button>
		</div>
	</a>
</div>

<style lang="postcss">
	.product-card {
		a {
			@apply flex h-60 w-40 cursor-pointer flex-col items-center gap-10 rounded-lg border-2 border-secondary-300 p-3 shadow-xl;

			transition: 200ms all ease-in-out;

			&:hover {
				@apply scale-105;
			}
		}

		img {
			@apply h-28 w-32 rounded-lg;
		}

		&__info {
			@apply w-full;

			&__name {
				@apply text-left text-base font-bold text-contrast;
			}
			&__short-description {
				@apply mt-0 text-left text-xs text-secondary;
			}
		}

		&__purchase {
			@apply flex w-full items-center justify-between;

			&__price {
				@apply text-base font-bold text-contrast;
			}

			&__add-to-order {
				@apply flex h-8 w-8 cursor-pointer items-center justify-center rounded-xl border-none bg-primary text-lg text-light transition-colors;

				&:hover {
					@apply bg-primary-700;
				}
			}
		}
	}
</style>
