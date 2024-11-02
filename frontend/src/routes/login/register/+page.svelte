<script lang="ts">
	import NameInput from '@components/form/inputs/Name.svelte';
	import EmailInput from '@components/form/inputs/Email.svelte';
	import PasswordInput from '@components/form/inputs/Password.svelte';
	import type { PasswordInputProps } from '$lib/types';
	import { fly } from 'svelte/transition';
	import keyboardArrowLeft from '@icons/keyboard-arrow-left.svg';
	import { enhance } from '$app/forms';

	const retypePassword: PasswordInputProps = {
		id: 'retypePassword',
		label: 'Confirme sua senha',
		hasEye: false,
		oninput: oninput
	};

	let error: string = $state('');

	function oninput() {
		error = '';
	}
</script>

<section class="page">
	<header>
		<h1>Crie sua conta</h1>

		<section class="go-back">
			<button type="button" class="primary">
				<a href="/">
					<img src={keyboardArrowLeft} alt="Arrow-left" />
				</a>
			</button>
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
				}
			};
		}}
	>
		<NameInput />
		<EmailInput />
		<PasswordInput {oninput} />
		<PasswordInput {...retypePassword} />

		{#if error}
			<p class="form__error" transition:fly={{ duration: 500, x: -150 }}>{error}</p>
		{/if}

		<div class="form__submit-area">
			<button class="primary">Cadastre-se</button>
			<p>Já possui uma conta? <a href="/login">Entre</a></p>
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
