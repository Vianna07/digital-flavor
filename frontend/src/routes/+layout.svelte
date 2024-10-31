<script lang="ts">
	import '../app.css';

	let { children } = $props();
</script>

<main class="flex h-full w-full flex-col items-center justify-center">
	{@render children()}
</main>

<style></style>
