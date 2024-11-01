<script lang="ts">
	import NameInput from '@components/form/inputs/Name.svelte';
	import EmailInput from '@components/form/inputs/Email.svelte';
	import PasswordInput from '@components/form/inputs/Password.svelte';
	import type { PasswordInputProps } from '$lib/types';

	const password: PasswordInputProps = {
		id: 'retype-password',
		hasEye: false,
	};
</script>

<form class="flex flex-col gap-5" action="">
	<NameInput />
	<EmailInput />
	<PasswordInput />
	<PasswordInput {...password} />
</form>

<style>
</style>
