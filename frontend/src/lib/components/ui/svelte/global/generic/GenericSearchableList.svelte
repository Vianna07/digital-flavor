<script lang="ts" generics="T extends { id: string }">
	import GenericList from './GenericList.svelte';
	import SearchInput from '../SearchInput.svelte';
	import type { GenericSearchableListProps } from '$lib/types';

	let { list, searchInput, style }: GenericSearchableListProps<T> = $props();
</script>

<div class={`listing ${style}`}>
  <SearchInput {...searchInput} />
  <GenericList {...list} />
</div>

<style lang="postcss">
  .listing {
    @apply flex w-full flex-col;
  }
</style>
