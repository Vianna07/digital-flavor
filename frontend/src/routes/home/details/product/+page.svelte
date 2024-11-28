<script lang="ts">
	import type { ProductDetails } from "$lib/types";
	import GoBack from "@components/global/GoBack.svelte";

	let product: ProductDetails = {
		name: "Café Ghourmet",
		shortDescription: "Forte",
		description:
			"Um cappuccino é uma bebida de aproximadamente 150 ml (5 oz), com 25 ml de café expresso e 85 ml de leite fresco",
		price: 1.99,
		imageUrl: "https://th.bing.com/th/id/OIP.JW4deLVxEH2nEwQHqQ3CAwEyDM?w=7360&h=4912&rs=1&pid=ImgDetMain"
	};
</script>

<div class="page">
	<header>
		<GoBack redirectUrl={"/home"} />
		<h1 class="simple-title">Detalhes do Produto</h1>
	</header>

	<div class="product-content">
		<img
			src={product.imageUrl}			alt={product.name}
			class="product-content__image"
		/>

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
		<button class="add-to-cart-button">
			Adicionar ao Carrinho
		</button>
	</footer>
</div>

<style lang="postcss">
	.page {
		@apply min-h-screen flex flex-col;
	
		header {
			@apply flex items-center h-14 my-3.5;
		}
		.product-content{
			@apply flex flex-col h-14 max-w-md flex-grow;
			
			&__image {
				@apply w-full h-56 object-cover rounded-lg mb-4;
			}
	
			&__name {
				@apply text-xl font-bold text-secondary-950 mb-1;
			}
	
			&__short__description {
				@apply text-placeholder mb-3;
			}
	
			&__divider {
				@apply border-placeholder mb-3;
			}
	
			&__description__title {
				@apply w-20 h-6 text-lg font-semibold text-contrast;
			}
	
			&__description {
				@apply text-secondary mb-4 w-72 h-20 text-sm font-normal leading-6;
			}
		}
		
		
		footer {
			@apply flex justify-between items-center px-2.5 py-2.5 mt-auto gap-8;
	
			.price {
				@apply text-left w-16 h-10;
	
				&__label {
					@apply block text-sm text-placeholder;
				}
	
				&__value {
					@apply text-base font-bold text-primary;
				}
			}
	
			.add-to-cart-button {
				@apply flex flex-row justify-center text-sm items-center w-44 h-9 bg-primary text-secondary-50 rounded-2xl hover:bg-primary-500 transition duration-speed;
			}
		}
	}
	</style>