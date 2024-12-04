<script lang="ts">
	import GoBack from "@components/global/GoBack.svelte";
	import addIcon from '@icons/add.svg';
	import removeIcon from '@icons/remove.svg';

	let orderItems = [
		{ name: "Café Gourmet", quantity: 3, price: 5.97 },
		{ name: "Café Java", quantity: 2, price: 3.0 },
		{ name: "Tapioca", quantity: 1, price: 3.5 },
		{ name: "Lanche", quantity: 2, price: 3.98 }
	];

	let totalPrice = orderItems.reduce((total, item) => total + (item.quantity * item.price), 0);
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
		totalPrice = orderItems.reduce((total, item) => total + (item.quantity * item.price), 0);
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
				<span class="item-price">R$ {(item.quantity * item.price).toFixed(2)}</span>
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
		@apply flex flex-col min-h-screen items-center gap-6 py-4;

		header {
			@apply w-full flex justify-between items-center mt-4;
		}

		.payment-methods {
			@apply flex justify-between gap-4 mb-6 w-full max-w-md;

			.payment-method {
				@apply px-6 py-3 rounded-full bg-secondary text-secondary-50 text-sm shadow-md transition duration-300;
			}

			.payment-method.active {
				@apply bg-primary text-secondary-50 shadow-lg;
			}
		}

		.order-items {
			@apply flex flex-col gap-4 w-full max-w-md;

			.order-item {
				@apply flex justify-between items-center bg-secondary-50 rounded-lg shadow-sm p-4;

				.quantity {
					@apply flex items-center gap-2 text-primary;

					.button {
						@apply flex items-center justify-center w-5 h-5 bg-primary rounded-lg shadow-sm transition duration-speed;

						.icon {
							@apply w-6 h-6;
							filter: invert(100%) sepia(0%) saturate(0%) hue-rotate(0deg) brightness(100%) contrast(100%);
						}
					}

					.quantity-value {
						@apply text-base font-bold;
					}
				}

				.item-name {
					@apply text-sm font-medium text-contrast px-2;
				}

				.item-price {
					@apply text-sm font-semibold text-primary;
				}
			}
		}

		footer {
			@apply mt-auto flex items-center justify-between gap-4 w-full max-w-md;

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
				@apply flex h-9 px-6 items-center justify-center rounded-xl bg-primary text-sm text-secondary-50 transition duration-speed hover:bg-primary-500;
			}
		}
	}
</style>
