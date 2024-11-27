<script lang="ts">
	import NavFooter from '@components/global/NavFooter.svelte';

	let { children } = $props();
</script>

<main class="main">
	{@render children()}
	<NavFooter/>
</main>

<style lang="postcss">
	.main {
		@apply flex h-full w-full flex-col items-center justify-center;
	}
</style>
