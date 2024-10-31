<script lang="ts">
	import BaseInput from './Base.svelte';
	import type { InputProps } from '$lib/types';
	import emailIcon from '$lib/assets/icons/envelope.svg';

	const inputProps: InputProps = $state({
		id: 'email',
		type: 'email',
		label: 'Digite seu e-mail',
		leftIconUrl: emailIcon
	});
</script>

<BaseInput {...inputProps} />
