<script lang="ts">
	import NavFooter from '@components/global/NavFooter.svelte';

	let { children } = $props();
</script>

<main class="main">
	{@render children()}
	<NavFooter/>
</main>
