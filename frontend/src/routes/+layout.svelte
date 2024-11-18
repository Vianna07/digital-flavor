<script lang="ts">
	import '../app.css';

	let { children } = $props();
</script>

<main class="main">
	{@render children()}
</main>
