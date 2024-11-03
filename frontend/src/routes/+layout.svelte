<script lang="ts">
	import '../app.css';

	let { children } = $props();
</script>

<main class="main">
	{@render children()}
</main>

<style>
	.main {
		@apply flex h-full w-full flex-col items-center justify-center;
	}
</style>
