<script lang="ts">
	import GoBack from '@components/global/GoBack.svelte';
	import { fly } from 'svelte/transition';
	import { enhance } from '$app/forms';
	import { goto } from '$app/navigation';
	import type { GenericInputProps, GenericTextareaProps } from '$lib/types';
	import GenericInput from '@components/global/generic/GenericInput.svelte';
	import GenericTextarea from '@components/global/generic/GenericTextarea.svelte';
	import ChooseImage from '@components/global/ChooseImage.svelte';

	const nameInputProps: GenericInputProps = {
		id: 'name',
		type: 'text',
		label: 'Digite o nome do produto',
		leftIconUrl: undefined
	};

	const shortDescriptionInputProps: GenericInputProps = {
		id: 'short-description',
		type: 'text',
		label: 'Breve descrição',
		required: false,
		maxlength: 25
	};

	const priceInputProps: GenericInputProps = {
		id: 'price',
		type: 'number',
		label: 'Valor',
		step: '.01'
	};

	const stockInputProps: GenericInputProps = {
		id: 'stock',
		type: 'number',
		label: 'Estoque',
		required: false,
		step: '1'
	};

	const descriptionTextareaProps: GenericTextareaProps = {
		id: 'description',
		placeholder: 'Descreva seu produto de forma mais detalhada',
		required: false
	};

	let error: string = $state('');

	function oninput() {
		error = '';
	}
</script>

<section class="page">
	<header>
		<GoBack redirectUrl={'/home'} />
		<h1 class="simple-title--lg">Cadastro de produto</h1>
	</header>

	<form
		method="POST"
		enctype="multipart/form-data"
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
		<GenericInput {...nameInputProps} />
		<GenericInput {...shortDescriptionInputProps} />

		<div class="flex gap-5">
			<GenericInput {...priceInputProps} />
			<GenericInput {...stockInputProps} />
		</div>

		<hr />

		<GenericTextarea {...descriptionTextareaProps} />

		<ChooseImage label={'Coloque a imagem do produto'} />

		{#if error}
			<p class="form__error" transition:fly={{ duration: 500, x: -175 }}>{error}</p>
		{/if}

		<button class="primary submit-button"> Cadastrar produto </button>
	</form>
</section>

<style lang="postcss">
	.page {
		@apply mt-5 justify-start gap-10;

		header {
			@apply flex;
		}

		form {
			@apply relative flex w-full flex-col gap-8;

			.submit-button {
				@apply mb-8;
			}
		}
	}
</style>
