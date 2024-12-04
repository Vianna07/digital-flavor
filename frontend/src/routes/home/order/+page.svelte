<script lang="ts">
	import GoBack from '@components/global/GoBack.svelte';
	import addIcon from '@icons/add.svg';
	import removeIcon from '@icons/remove.svg';

	let orderItems = [
		{ name: 'Café Gourmet', quantity: 3, price: 5.97 },
		{ name: 'Café Java', quantity: 2, price: 3.0 },
		{ name: 'Tapioca', quantity: 1, price: 3.5 },
		{ name: 'Lanche', quantity: 2, price: 3.98 }
	];

	let totalPrice = orderItems.reduce((total, item) => total + item.quantity * item.price, 0);
	let selectedPaymentMethod = 'Pix';

	function selectPaymentMethod(method: string) {
		selectedPaymentMethod = method;
	}

	function incrementQuantity(index: number) {
		orderItems[index].quantity += 1;
		updateTotal();
	}

	function decrementQuantity(index: number) {
		orderItems[index].quantity -= 1;

		if (orderItems[index].quantity <= 0) {
			orderItems.splice(index, 1);
		}

		updateTotal();
	}

	function updateTotal() {
		totalPrice = orderItems.reduce((total, item) => total + item.quantity * item.price, 0);
	}
</script>

<div class="page">
	<header>
		<GoBack redirectUrl={'/home'} />
		<h1 class="simple-title--lg">Pedido</h1>
	</header>

	<div class="payment-methods">
		<button
			class="payment-method {selectedPaymentMethod === 'Pix' ? 'active' : ''}"
			on:click={() => selectPaymentMethod('Pix')}
		>
			Pix
		</button>
		<button
			class="payment-method {selectedPaymentMethod === 'Cartão' ? 'active' : ''}"
			on:click={() => selectPaymentMethod('Cartão')}
		>
			Cartão
		</button>
		<button
			class="payment-method {selectedPaymentMethod === 'Dinheiro' ? 'active' : ''}"
			on:click={() => selectPaymentMethod('Dinheiro')}
		>
			Dinheiro
		</button>
	</div>

	<div class="order-items">
		{#each orderItems as item, index}
			<div class="order-item">
				<div class="quantity">
					<button on:click={() => incrementQuantity(index)} class="button">
						<img src={addIcon} alt="Adicionar item" class="icon" />
					</button>
					<span class="quantity-value">{item.quantity}</span>
					<button on:click={() => decrementQuantity(index)} class="button">
						<img src={removeIcon} alt="Remover item" class="icon" />
					</button>
				</div>
				<span class="item-name">{item.name}</span>
				<span class="item-price"
					>{(item.quantity * item.price).toLocaleString('pt-br', {
						style: 'currency',
						currency: 'BRL'
					})}</span
				>
			</div>
		{/each}
	</div>

	<footer>
		<div class="price">
			<span class="price__label">Total</span>
			<span class="price__value">R$ {totalPrice.toFixed(2)}</span>
		</div>
		<button class="add-to-cart-button">Pagar</button>
	</footer>
</div>

<style lang="postcss">
	.page {
		@apply flex min-h-screen flex-col items-center gap-6 py-4;

		header {
			@apply mt-4 flex w-full items-center justify-between;
		}

		.payment-methods {
			@apply mb-6 flex w-full max-w-md justify-between gap-4;

			.payment-method {
				@apply rounded-full bg-secondary px-6 py-3 text-sm text-secondary-50 shadow-md transition duration-300;
			}

			.payment-method.active {
				@apply bg-primary text-secondary-50 shadow-lg;
			}
		}

		.order-items {
			@apply flex w-full max-w-md flex-col gap-4;

			.order-item {
				@apply flex items-center justify-between rounded-lg bg-secondary-50 p-4 shadow-sm;

				.quantity {
					@apply flex items-center gap-2 text-primary;

					.button {
						@apply flex h-5 w-5 items-center justify-center rounded-lg bg-primary shadow-sm transition duration-speed;

						.icon {
							@apply h-6 w-6;
							filter: invert(100%) sepia(0%) saturate(0%) hue-rotate(0deg) brightness(100%)
								contrast(100%);
						}
					}

					.quantity-value {
						@apply text-base font-bold;
					}
				}

				.item-name {
					@apply px-2 text-sm font-medium text-contrast;
				}

				.item-price {
					@apply text-sm font-semibold text-primary;
				}
			}
		}

		footer {
			@apply mt-auto flex w-full max-w-md items-center justify-between gap-4;

			.price {
				@apply flex flex-col text-left;

				&__label {
					@apply text-sm text-placeholder;
				}

				&__value {
					@apply text-base font-bold text-primary;
				}
			}

			.add-to-cart-button {
				@apply flex h-9 items-center justify-center rounded-xl bg-primary px-6 text-sm text-secondary-50 transition duration-speed hover:bg-primary-500;
			}
		}
	}
</style>
