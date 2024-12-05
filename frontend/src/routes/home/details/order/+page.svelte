<script lang="ts">
  import addIcon from '@icons/add.svg';
  import removeIcon from '@icons/remove.svg';
	import { PaymentMethod, type GenericListProps, type Order } from '$lib/types';
	import GenericList from '@components/global/generic/GenericList.svelte';
  import GoBack from '@components/global/GoBack.svelte';
  import { orderItems } from '@stores/order'
	import { page } from '$app/stores';
	import { goto } from '$app/navigation';

  const FETCH_URL = `/api${$page.url.pathname}`;

  let products: GenericListProps<Order> = $state({
    data: typeof window !== 'undefined' ? [...JSON.parse(localStorage.getItem('order-products') as string) || new Map()].map(([id, obj]) => ({id,...obj})) : undefined,
    fields: {
      title: 'name'
    },
    left: {
      snippet: quantity
    },
    right: {
      snippet: price
    },
    contentStyle: {
      title: 'text-left'
    }
  });

	let totalPrice = $state(products.data?.reduce((total, item) => total + item.quantity * item.price, 0));
	let selectedPaymentMethod: PaymentMethod = $state(PaymentMethod.PIX);

	function selectPaymentMethod(method: PaymentMethod) {
		selectedPaymentMethod = method;
	}

	function incrementQuantity(index: number) {
    if (products.data) {
      products.data[index].quantity = Math.min(products.data[index].quantity + 1, 99);

      const id: string = (products.data[index].id)

      orderItems.update((current) => {
        const newOrder = new Map(current);

        newOrder.set(id, {
          name: newOrder.get(id)?.name as string,
          price: newOrder.get(id)?.price as number,
          quantity: (newOrder.get(id)?.quantity || 0) + 1,
        });

        return newOrder;
      });

      updateTotal();
    }
  }

	function decrementQuantity(index: number) {
    if (products.data) {
      products.data[index].quantity -= 1;

      const id: string = (products.data[index].id)

      orderItems.update((current) => {
        const newOrder = new Map(current);

        newOrder.set(id, {
          name: newOrder.get(id)?.name as string,
          price: newOrder.get(id)?.price as number,
          quantity: (newOrder.get(id)?.quantity || 0) - 1,
        });

        return newOrder;
      });

      if (products.data[index].quantity <= 0) {
			  products.data.splice(index, 1);
		  }

		  updateTotal();
    }
	}

	function updateTotal() {
		totalPrice = products.data?.reduce((total, item) => total + item.quantity * item.price, 0);
	}

  async function pay() {
    try {
      await fetch(FETCH_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ orderItems: typeof window !== 'undefined' ? localStorage.getItem('order-products') as string || undefined : undefined, paymentMethod: selectedPaymentMethod.toString() }),
      });

      localStorage.removeItem('order-products');
      orderItems.set(new Map())

      goto('/home')
    } catch (error) {
      console.error(error);
    }
  }
</script>

{#snippet quantity(index: number)}
  <div class="quantity-area">
    <button onclick={() => incrementQuantity(index)} class="button">
      <img src={addIcon} alt="Adicionar item" class="icon--white" />
    </button>
    <p class="quantity">
      {products.data?.[index].quantity}
    </p>
    <button onclick={() => decrementQuantity(index)} class="button">
      <img src={removeIcon} alt="Remover item" class="icon--white" />
    </button>
  </div>
{/snippet}

{#snippet price(index: number)}
  <h2 class="price">
    {((products.data?.[index].quantity || 0) * (products.data?.[index].price || 0)).toLocaleString('pt-br', {
    style: 'currency',
    currency: 'BRL'
  })}</h2
  >
{/snippet}

<div class="page">
	<header>
		<GoBack redirectUrl={'/home'} />
		<h1 class="simple-title--lg">Pedido</h1>
	</header>

	<div class="payment-methods">
		<button
			class="payment-method {selectedPaymentMethod === PaymentMethod.PIX ? 'active' : ''}"
			onclick={() => selectPaymentMethod(PaymentMethod.PIX)}
		>
			Pix
		</button>
		<button
			class="payment-method {selectedPaymentMethod === PaymentMethod.CARD ? 'active' : ''}"
			onclick={() => selectPaymentMethod(PaymentMethod.CARD)}
		>
			Cartão
		</button>
		<button
			class="payment-method {selectedPaymentMethod === PaymentMethod.CASH ? 'active' : ''}"
			onclick={() => selectPaymentMethod(PaymentMethod.CASH)}
		>
			Dinheiro
		</button>
	</div>

  <GenericList {...products} />

	<footer>
		<div class="price">
			<h2 class="price__label">Total</h2>
			<h1 class="price__value">{totalPrice?.toLocaleString('pt-br', {
        style: 'currency',
        currency: 'BRL'
      })}</h1>
		</div>
		<button type="button" onclick={pay}>Pagar</button>
	</footer>
</div>

<style lang="postcss">
  .quantity-area {
    @apply flex items-center justify-between gap-2 text-primary w-20;

    .quantity {
      @apply text-base font-bold;
    }

    button {
    @apply flex h-5 w-5 items-center justify-center rounded-lg bg-primary shadow-sm transition duration-speed;

      .icon {
        @apply h-6 w-6;
      }
    }
  }

  .price {
    @apply w-20 text-left text-sm font-semibold text-primary;
  }

	.page {
		@apply flex flex-col items-center gap-6 py-4;

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

    .item-name {
      @apply px-2 text-sm font-medium text-contrast;
    }

    .item-price {
      @apply text-sm font-semibold text-primary;
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

			button {
				@apply flex h-9 items-center justify-center rounded-xl bg-primary px-6 text-sm text-secondary-50 transition duration-speed hover:bg-primary-500;
			}
		}
	}
</style>
