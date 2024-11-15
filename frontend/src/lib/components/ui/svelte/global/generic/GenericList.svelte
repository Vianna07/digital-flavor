<script lang="ts" generics="T extends { id: string }">
	import { type GenericListProps } from '$lib/types';
	import { flip } from 'svelte/animate';

	let {
		data,
		fields,
		left,
		onclick,
		noDataMessage = 'Nenhum dado encontrado'
	}: GenericListProps<T> = $props();
</script>

<ul class="list">
	{#if data.length}
		{#each data as data (data.id)}
			<li class="list__item" animate:flip>
				<button type="button" onclick={() => onclick?.(data.id)}>
					<div>
						{@render left?.snippet(data[left.field])}
					</div>
					<div class="list__item__content">
						<h1 class="list__item__title">{data[fields.title]}</h1>
						<h3 class="list__item__sub-title">{data[fields.subTitle]}</h3>
					</div>
				</button>
			</li>
		{/each}
	{:else}
		<h1 class="list__no-data-message">{noDataMessage}</h1>
	{/if}
</ul>

<style lang="postcss">
	.list {
		@apply flex h-full w-full flex-col items-center justify-start gap-5 overflow-y-scroll rounded-md border-2 pr-1.5;

		&__no-data-message {
			@apply relative top-1/2 -translate-y-1/2 p-3 font-bold;
		}

		&__item {
			@apply h-20 w-full cursor-pointer rounded-lg border-2 border-primary bg-secondary-50 p-3 text-center shadow-md transition-transform;

			button {
				@apply flex h-full w-full items-center gap-4;
			}

			transition: transform 0.3s ease;

			&:hover {
				transform: scale(0.95);
			}

			&__content {
				@apply w-9/12 text-center;
			}

			&__title {
				@apply text-sm font-bold;
			}

			&__sub-title {
				@apply text-sm text-secondary;
			}
		}
	}
</style>
