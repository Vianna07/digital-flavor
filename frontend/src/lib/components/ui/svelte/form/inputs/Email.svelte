<script lang="ts">
	import BaseInput from './Base.svelte';
	import type { BaseInputProps, InputProps } from '$lib/types';
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

	const inputProps: BaseInputProps = $state({
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

<BaseInput {...inputProps} />
