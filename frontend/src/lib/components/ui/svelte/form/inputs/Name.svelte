<script lang="ts">
	import BaseInput from './Base.svelte';
	import type { InputProps } from '$lib/types';
	import userIcon from '$lib/assets/icons/user.svg';

	const inputProps: InputProps = $state({
		id: 'name',
		type: 'text',
		label: 'Digite seu nome',
		leftIconUrl: userIcon,
		pattern: '^[a-zA-Z ]+'
	});
</script>

<BaseInput {...inputProps} />
