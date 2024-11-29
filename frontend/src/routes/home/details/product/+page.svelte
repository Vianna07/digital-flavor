<script lang="ts">
	import type { ProductDetails } from '$lib/types';
	import GoBack from '@components/global/GoBack.svelte';

	let product: ProductDetails = {
		name: 'Café Ghourmet',
		shortDescription: 'Forte',
		description:
			'Um cappuccino é uma bebida de aproximadamente 150 ml (5 oz), com 25 ml de café expresso e 85 ml de leite fresco',
		price: 1.99,
		imageUrl:
			'https://th.bing.com/th/id/OIP.JW4deLVxEH2nEwQHqQ3CAwEyDM?w=7360&h=4912&rs=1&pid=ImgDetMain'
	};
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

		<hr class="product-content__divider" />

		<h3 class="product-content__description__title">Descrição</h3>
		<p class="product-content__description">
			{product.description}
		</p>
	</div>

	<footer>
		<div class="price">
			<span class="price__label">Preço</span>
			<span class="price__value">R$ {product.price.toFixed(2)}</span>
		</div>
		<button class="add-to-cart-button"> Adicionar ao Carrinho </button>
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
				@apply mb-4 h-56 w-full rounded-lg object-cover;
			}

			&__name {
				@apply mb-1 text-xl font-bold text-secondary-950;
			}

			&__short__description {
				@apply mb-3 text-placeholder;
			}

			&__divider {
				@apply mb-3 border-placeholder;
			}

			&__description__title {
				@apply h-6 w-20 text-lg font-semibold text-contrast;
			}

			&__description {
				@apply h-20 w-72 text-sm font-normal leading-6 text-secondary;
			}
		}

		footer {
			@apply w-full flex items-center justify-between gap-8;

			.price {
				@apply h-10 w-16 text-left;

				&__label {
					@apply block text-sm text-placeholder;
				}

				&__value {
					@apply text-base font-bold text-primary;
				}
			}

			.add-to-cart-button {
				@apply flex h-9 w-44 flex-row items-center justify-center rounded-2xl bg-primary text-sm text-secondary-50 transition duration-speed hover:bg-primary-500;
			}
		}
	}
</style>
