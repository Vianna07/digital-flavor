<script lang="ts">
	import type { FormProps, PasswordInputProps } from '$lib/types';
	import GoBack from '../global/GoBack.svelte';
	import { fly } from 'svelte/transition';
	import { enhance } from '$app/forms';

	import NameInput from '@components/form/inputs/Name.svelte';
	import EmailInput from '@components/form/inputs/Email.svelte';
	import PasswordInput from '@components/form/inputs/Password.svelte';
	import { goto } from '$app/navigation';

	let { title, submit, inputs }: FormProps = $props();

	let error: string = $state('');

	const confirmPassword: PasswordInputProps = {
		id: 'retypePassword',
		label: 'Confirme sua senha',
		hasEye: false,
		oninput: oninput
	};

	function oninput() {
		error = '';
	}
</script>

<section class="page">
	<header>
		<h1>{title}</h1>

		<section class="go-back">
			<GoBack redirectUrl={'/canteen'} />
		</section>
	</header>

	<form
		method="POST"
		class="form"
		use:enhance={({ cancel }) => {
			return async ({ result }) => {
				if (result.type === 'failure') {
					error = result.data?.message as string;
					cancel();
				} else if (result.type === 'redirect') {
					goto(result?.location as string);
				}
			};
		}}
	>
		{#if inputs?.name}
			<NameInput {oninput} />
		{/if}

		<EmailInput {oninput} />
		<PasswordInput {oninput} />

		{#if inputs?.confirmPassword}
			<PasswordInput {...confirmPassword} />
		{/if}

		{#if error}
			<p class="form__error" transition:fly={{ duration: 500, x: -175 }}>{error}</p>
		{/if}

		<div class="form__submit-area">
			<button class="primary">{submit.text}</button>
			<p>
				{submit.paragraph.text}
				<a href={submit.paragraph.link.href}>{submit.paragraph.link.text}</a>
			</p>
		</div>
	</form>
</section>

<style lang="postcss">
	.page {
		@apply flex flex-col gap-10;

		header {
			@apply text-center text-4xl font-bold text-primary;

			.go-back {
				@apply absolute top-10;
			}
		}

		.form {
			@apply relative flex flex-col gap-5;

			&__error {
				@apply absolute bottom-28 h-6 w-full text-center text-base font-semibold text-secondary-600 underline;
			}

			&__submit-area {
				@apply mt-10 flex flex-col gap-1 text-center;

				a {
					@apply font-bold text-contrast;

					&:hover {
						@apply underline;
					}
				}
			}
		}
	}
</style>
