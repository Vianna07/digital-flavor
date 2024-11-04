<script lang="ts">
	import GenericInput from '@components/global/GenericInput.svelte';
	import lockIcon from '$lib/assets/icons/lock.svg';
	import eyeSlashIcon from '$lib/assets/icons/eye-slash.svg';
	import eyeIcon from '$lib/assets/icons/eye.svg';
	import type { GenericInputProps, PasswordInputProps } from '$lib/types';

	let {
		id = 'password',
		type = 'password',
		label = 'Digite sua senha',
		leftIconUrl,
		rightIcon,
		pattern,
		required,
		oninput,
		hasEye = true
	}: PasswordInputProps = $props();

	let urlIndex: number = 0;
	const urls: Map<number, string> = new Map([
		[0, eyeSlashIcon],
		[1, eyeIcon]
	]);

	const inputProps: GenericInputProps = $state({
		id: id,
		type: type,
		label: label,
		leftIconUrl: leftIconUrl ?? lockIcon,
		pattern: pattern,
		rightIcon:
			rightIcon ??
			(hasEye
				? {
						url: eyeSlashIcon,
						onclick: function () {
							urlIndex = (urlIndex + 1) % 2;
							inputProps.type = urlIndex === 0 ? 'password' : 'text';
							this.url = urls.get(urlIndex) as string;
						}
					}
				: undefined),
		required: required,
		oninput: oninput
	});
</script>

<GenericInput {...inputProps} />
