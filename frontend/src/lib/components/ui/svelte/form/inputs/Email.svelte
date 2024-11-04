<script lang="ts">
	import GenericInput from '@components/global/GenericInput.svelte';
	import type { GenericInputProps, InputProps } from '$lib/types';
	import emailIcon from '$lib/assets/icons/envelope.svg';

	let {
		id = 'email',
		type = 'email',
		label = 'Digite seu e-mail',
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
		leftIconUrl: leftIconUrl ?? emailIcon,
		rightIcon: rightIcon,
		pattern: pattern,
		required: required,
		oninput: oninput
	});
</script>

<GenericInput {...inputProps} />
