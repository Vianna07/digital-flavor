<script lang="ts">
	import { goto } from '$app/navigation';
	import type { ProductCardProps } from '$lib/types';
	import addIcon from '@icons/add.svg';
	import editIcon from '@icons/edit.svg';
  import { orderItems } from '@stores/order'

	let {
		id,
		name,
		shortDescription,
		price,
		imageUrl,
		userType = 4,
	}: ProductCardProps = $props();
</script>

<div class="product-card">
	<a href={`/home/details/product/${id}`}>
		<img src={imageUrl} alt="Produto sem imagem" />

		<div class="product-card__info">
			<h3 class="product-card__info__name">{name.slice(0, 30)}</h3>
			<p class="product-card__info__short-description">{shortDescription}</p>
		</div>

		<div class="product-card__purchase">
			<h1 class="product-card__purchase__price">
				{price.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })}
			</h1>

			<div>
				<button class="product-card__purchase__add-to-order" onclick={(event) => {
          event.preventDefault();

          orderItems.update((current) => {
            const newOrder = new Map(current);

            newOrder.set(id, {
              name,
              price,
              quantity: (newOrder.get(id)?.quantity || 0) + 1,
            });

            return newOrder;
          });
        }}>
					<img class="icon--white" src={addIcon} alt="" />
				</button>

				{#if userType < 4}
					<button
						class="product-card__purchase__edit"
						onclick={(event) => {
							event.preventDefault();
							goto(`/home/edit/product/${id}`);
						}}
					>
						<img class="icon--white" src={editIcon} alt="" />
					</button>
				{/if}
			</div>
		</div>
	</a>
</div>

<style lang="postcss">
	.product-card {
		a {
			@apply flex h-60 w-40 cursor-pointer flex-col items-center gap-2 rounded-lg border-2 border-secondary-300 p-3 shadow-xl;

			transition: 200ms all ease-in-out;

			&:hover {
				@apply scale-105;
			}
		}

		img {
			@apply h-28 w-32 rounded-lg;
		}

		&__info {
			@apply w-full break-words overflow-auto;

			&__name {
				@apply text-left text-sm font-bold text-contrast;
			}
			&__short-description {
				@apply mt-0 text-left text-xs text-secondary;
			}
		}

		&__purchase {
			@apply mt-auto flex w-full items-center justify-between;

			&__price {
				@apply text-sm font-bold text-contrast;
			}

			div {
				@apply flex items-center justify-center gap-1;
			}

			&__add-to-order,
			&__edit {
				@apply flex h-7 w-7 cursor-pointer items-center justify-center rounded-xl border-none bg-primary text-lg text-light transition-colors;

				&:hover {
					@apply bg-primary-700;
				}

				img {
					@apply h-5 w-5;
				}
			}
		}
	}
</style>
