<script lang="ts">
	import BaseInput from './Base.svelte';
	import type { BaseInputProps, InputProps } from '$lib/types';
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

	const inputProps: BaseInputProps = $state({
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

<BaseInput {...inputProps} />
