<script lang="ts">
	import GenericInput from '@components/global/GenericInput.svelte';
	import type { GenericInputProps, InputProps } from '$lib/types';
	import userIcon from '$lib/assets/icons/user.svg';

	let {
		id = 'name',
		type = 'text',
		label = 'Digite seu nome',
		leftIconUrl,
		rightIcon,
		pattern,
		required,
		oninput
	}: InputProps = $props();

	const inputProps: GenericInputProps = $state({
		id: id,
		type: type,
		label: label,
		leftIconUrl: leftIconUrl ?? userIcon,
		rightIcon: rightIcon,
		pattern: pattern,
		required: required,
		oninput: oninput
	});
</script>

<GenericInput {...inputProps} />
