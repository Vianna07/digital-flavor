<script lang="ts">
	import '../app.css';

	let { children } = $props();
</script>

<main class="main">
	{@render children()}
</main>

<style lang="postcss">
	.main {
		@apply flex h-full w-full flex-col items-center justify-center;
	}
</style>
