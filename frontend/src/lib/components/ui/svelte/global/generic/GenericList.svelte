<script lang="ts" generics="T extends { id: string }">
	import { type GenericListProps } from '$lib/types';
	import { flip } from 'svelte/animate';

	let {
		listingType = 'open-listing',
		data,
		fields,
		left,
    right,
		onclick,
		noDataMessage = 'Nenhum dado encontrado',
		customListing,
    contentStyle,
	}: GenericListProps<T> = $props();
</script>

{#if listingType !== 'custom-listing'}
	<ul class={`list ${listingType}`}>
		{#if data?.length}
			{#each data as data, index (data.id)}
				<li class="list__item" animate:flip>
					<button type="button" onclick={() => onclick?.(data.id)}>
						{#if left?.snippet}
							<div class="list__item__left-content">
								{@render left.snippet(index, data[left?.field as keyof T])}
							</div>
						{/if}
						<div class={`list__item__content ${contentStyle?.content}`}>
							<h1 class={`list__item__title ${contentStyle?.title}`}>{data[fields?.title as keyof T]}</h1>
							<h3 class={`list__item__sub-title ${contentStyle?.subtitle}`}>{data[fields?.subTitle as keyof T]}</h3>
						</div>
            {#if right?.snippet}
              <div class="list__item__right-content">
                {@render right.snippet(index, data[right?.field as keyof T])}
              </div>
            {/if}
					</button>
				</li>
			{/each}
		{:else}
			<h1 class="list__no-data-message">{noDataMessage}</h1>
		{/if}
	</ul>
{:else}
	{@render customListing?.(data)}
{/if}

<style lang="postcss">
	.list {
		@apply flex h-full w-80 flex-col items-center justify-start gap-5 overflow-y-scroll pr-1.5 max-h-[380px];

		&__no-data-message {
			@apply relative top-1/2 -translate-y-1/2 p-3 font-bold;
		}

		&__item {
			@apply h-20 w-full cursor-pointer rounded-lg bg-secondary-50 p-3 text-center shadow-md transition-transform;

			transition: transform 0.3s ease;

			button {
				@apply flex h-full w-full items-center gap-4;

				&:focus-visible {
					@apply outline-none;
				}
			}

			&:hover,
			&:has(button:focus-visible) {
				transform: scale(0.95);
			}

			&__content {
				@apply w-9/12 text-center;
			}

			:not(:has(&__left-content)) {
				.list__item__content {
					@apply w-full;
				}
			}

			&__title {
				@apply text-sm font-bold;
			}

			&__sub-title {
				@apply text-sm text-secondary;
			}
		}
	}

	.open-listing {
		.list__item {
			@apply border-[0.125px] border-secondary-200;
		}
	}

	.closed-listing {
		@apply rounded-md border-2;

		.list__item {
			@apply border-2 border-primary;
		}
	}
</style>
