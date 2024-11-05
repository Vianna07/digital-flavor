<script lang="ts" generics="T extends { id: string }">
	import { type GenericListProps } from '$lib/types';

	let { list, fields, left, onclick }: GenericListProps<T> = $props();
</script>

<ul class="list">
	{#each list as data}
		<li class="list__item">
			<button type="button" onclick={() => onclick?.(data.id)}>
				<h1 class="list__item__title">{data[fields.title]}</h1>
				<h3 class="list__item__sub-title">{data[fields.subTitle]}</h3>
			</button>
		</li>
	{/each}
</ul>

<style lang="postcss">
	.list {
		@apply flex w-full flex-col items-center justify-center gap-5;

		&__item {
			@apply h-20 w-full cursor-pointer rounded-lg border-2 border-primary bg-secondary-50 p-3 text-center shadow-md transition-transform;

			transition: transform 0.3s ease;

			&:hover {
				transform: scale(1.05);
			}

			&__title {
				@apply text-base font-bold;
			}

			&__sub-title {
				@apply text-sm text-secondary;
			}
		}
	}
</style>
