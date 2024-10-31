<script lang="ts">
	import BaseInput from './Base.svelte';
	import lockIcon from '$lib/assets/icons/lock.svg';
	import eyeSlashIcon from '$lib/assets/icons/eye-slash.svg';
	import eyeIcon from '$lib/assets/icons/eye.svg';
	import type { InputProps, RightIcon } from '$lib/types';

	let hasEye: boolean = $props();

	let urlIndex: number = 0;
	const urls: Map<number, string> = new Map([
		[0, eyeSlashIcon],
		[1, eyeIcon]
	]);
	const rightIcon: RightIcon | undefined = hasEye
		? {
				url: eyeSlashIcon,
				onclick: function () {
					urlIndex = (urlIndex + 1) % 2;
					this.url = urls.get(urlIndex) as string;
				}
			}
		: undefined;

	const inputProps: InputProps = $state({
		id: 'password',
		type: 'password',
		label: 'Digite sua senha',
		leftIconUrl: lockIcon,
		rightIcon: rightIcon
	});
</script>

<BaseInput {...inputProps} />
