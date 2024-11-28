<script lang="ts">
	import GenericInput from '@components/global/generic/GenericInput.svelte';
	import searchIcon from '@icons/magnifying-glass.svg';
	import type { GenericInputProps, InputProps } from '$lib/types';

	let {
		id = 'search',
		type = 'text',
		label = 'Pesquise por algo',
		leftIconUrl = searchIcon,
		oninput,
		pattern,
		required,
		rightIcon
	}: InputProps = $props();

	const searchInput: GenericInputProps = {
		id,
		type,
		label,
		leftIconUrl,
		oninput,
		pattern,
		required,
		rightIcon
	};
</script>

<GenericInput {...searchInput} />
