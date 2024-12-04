<script lang="ts">
	import type { ProductDetails } from '$lib/types';
	import GoBack from '@components/global/GoBack.svelte';

	let { data }: { data: { product: ProductDetails } } = $props();

	const product = data.product;
</script>

<div class="page">
	<header>
		<GoBack redirectUrl={'/home'} />
		<h1 class="simple-title">Detalhes do Produto</h1>
	</header>

	<div class="product-content">
		<img src={product.imageUrl} alt={product.name} class="product-content__image" />

		<h2 class="product-content__name">{product.name}</h2>
		<p class="product-content__short__description">{product.shortDescription}</p>

		<hr />

		<h3 class="product-content__description__title">Descrição</h3>
		<p class="product-content__description">
			{product.description}
		</p>
	</div>

	<footer>
		<div class="price">
			<p class="price__label">Preço</p>
			<p class="price__value">R$ {product.price.toFixed(2)}</p>
		</div>
		<button class="primary add-to-cart-button"> Adicionar ao Carrinho </button>
	</footer>
</div>

<style lang="postcss">
	.page {
		@apply gap-8;

		header {
			@apply mt-4 flex h-14 items-center;
		}

		.product-content {
			@apply flex flex-grow flex-col;

			&__image {
				@apply mb-4 h-56 w-full rounded-lg border-2 border-secondary-300 bg-secondary-200 object-cover;
			}

			&__name {
				@apply mb-1 text-xl font-bold text-secondary-950;
			}

			&__short__description {
				@apply mb-3 text-placeholder;
			}

			hr {
				@apply my-4;
			}

			&__description__title {
				@apply h-6 w-20 text-lg font-semibold text-contrast;
			}

			&__description {
				@apply h-20 w-72 text-sm font-normal leading-6 text-secondary;
			}
		}

		footer {
			@apply mt-auto flex w-full items-center justify-between gap-8;

			.price {
				@apply h-10 text-left;

				&__label {
					@apply text-sm text-placeholder;
				}

				&__value {
					@apply text-base font-bold text-primary;
				}
			}

			.add-to-cart-button {
				@apply flex h-10 items-center justify-center;
			}
		}
	}
</style>
