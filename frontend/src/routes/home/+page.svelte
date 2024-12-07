<script lang="ts">
	import {
		type Canteen,
		type GenericListProps,
		type GenericSearchableListProps,
		type ProductCardProps
	} from '$lib/types';
	import addIcon from '@icons/add.svg';
	import ProductCard from '@components/global/ProductCard.svelte';
	import { goto } from '$app/navigation';
	import GenericSearchableList from '@components/global/generic/GenericSearchableList.svelte';
	import { page } from '$app/stores';
	import { flip } from 'svelte/animate';
	import shoppingCardIcon from '@icons/shopping-cart.svg';
  import { orderQuantityTotal } from '@stores/order'

	let { data }: { data: { products: ProductCardProps[]; userType: number; canteen: Canteen } } =
		$props();

	const FETCH_URL = `/api${$page.url.pathname}`;

	let products: GenericListProps<ProductCardProps> = $state({
		data: data.products,
		listingType: 'custom-listing',
		customListing: productListing
	});

	const searchInput = {
		label: 'Pesquise pelo produto',
		oninput: async (name: string) => {
			try {
				if (name) {
					const response: Response = await fetch(FETCH_URL, {
						method: 'POST',
						headers: {
							'Content-Type': 'application/json'
						},
						body: JSON.stringify({ name })
					});

					products.data = await response.json();
				}
			} catch (error) {
				console.error(error);
			}
		}
	};

	let searchableList: GenericSearchableListProps<ProductCardProps> = {
		list: products,
		searchInput
	};
</script>

{#snippet productListing(products: ProductCardProps[] | undefined)}
	<div class="product-listing">
		{#if data.userType < 4}
			<button class="new-product-card" onclick={() => goto('/home/create/product')}>
				<a href="/home/create/product" class="primary">
					<img class="icon--white" src={addIcon} alt="add-icon" />
				</a>
				<h1>Cadastrar produto</h1>
			</button>
		{/if}

		{#if products}
			{#each products as product (product.id)}
				<div animate:flip={{ duration: 400 }}>
					<ProductCard {...product} userType={data.userType} />
				</div>
			{/each}
		{/if}
	</div>
{/snippet}

<div class="page">
	<header>
		<p>{data.canteen.address}</p>

		<div>
			<h1 class="simple-title">{data.canteen.name}</h1>
			<button onclick={() => {
        if ($orderQuantityTotal) {
          goto("/home/details/order")
        }
      }}>
				<img class:cursor-default={!$orderQuantityTotal} class="icon--red" src={shoppingCardIcon} alt="" />

        {#if $orderQuantityTotal}
          <div>
            <p>
              {$orderQuantityTotal}
            </p>
          </div>
        {/if}
			</button>
		</div>
	</header>

	<GenericSearchableList {...searchableList} />
</div>

<style lang="postcss">
	.product-listing {
		@apply flex flex-wrap justify-center gap-4;

		.new-product-card {
			@apply flex h-60 w-40 flex-col items-center justify-center gap-3 rounded-lg border-2 border-secondary-400 p-3 shadow-2xl;

			transition: 200ms all ease-in-out;

			&:hover {
				@apply scale-[1.02];
			}

			a {
				@apply flex h-20 w-20 items-center justify-center rounded-[100%];

				transition: 200ms all ease-in-out;

				&:hover {
					@apply scale-105;
				}

				img {
					@apply h-10 w-10;
				}
			}

			h1 {
				@apply text-center text-lg font-bold;
			}
		}
	}

	.page {
		@apply w-[21rem];

		header {
			@apply flex flex-col gap-2;
			p {
				@apply text-xs text-gray-600;
			}

			div {
				@apply flex items-start justify-between;

				h1 {
					@apply ml-0 mt-0 text-left;
				}

        button {
          @apply relative;

          div {
            @apply absolute -top-3 -right-1.5 bg-primary rounded-full py-1 w-6 text-center opacity-85;

            p {
              @apply text-secondary-50 w-full;
            }
          }

          img {
            @apply h-7 w-7 cursor-pointer;
          }

          img.cursor-default {
            cursor: default;
          }
        }
			}
		}
	}
</style>
