<script lang="ts">
	import NavFooter from '@components/global/NavFooter.svelte';

	let { children } = $props();
</script>

{@render children()}
<NavFooter />
