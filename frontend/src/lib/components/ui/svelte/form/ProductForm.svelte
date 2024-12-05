<script lang="ts">
	import GoBack from '@components/global/GoBack.svelte';
	import { enhance } from '$app/forms';
	import { goto } from '$app/navigation';
	import type { ChooseImageProps, GenericInputProps, GenericTextareaProps } from '$lib/types';
	import GenericInput from '@components/global/generic/GenericInput.svelte';
	import GenericTextarea from '@components/global/generic/GenericTextarea.svelte';
	import ChooseImage from '@components/global/ChooseImage.svelte';
	import type { ProductFormProps } from '$lib/types';

	let {
		id = '',
		name = '',
		shortDescription = '',
		price,
		description = '',
		imageUrl = '',
		submitButtonLabel
	}: ProductFormProps = $props();

	const nameInputProps: GenericInputProps = {
		id: 'name',
		type: 'text',
		label: 'Digite o nome do produto',
		leftIconUrl: undefined,
		value: name
	};

	const shortDescriptionInputProps: GenericInputProps = {
		id: 'short-description',
		type: 'text',
		label: 'Breve descrição',
		required: false,
		maxlength: 20,
		value: shortDescription
	};

	const priceInputProps: GenericInputProps = {
		id: 'price',
		type: 'number',
		label: 'Valor',
		step: '.01',
		max: 99,
		value: String(price)
	};

	// const stockInputProps: GenericInputProps = {
	// 	id: 'stock',
	// 	type: 'number',
	// 	label: 'Estoque',
	// 	required: false,
	// 	step: '1',
	//   max: 32000,
	//   value: String(stock)
	// };

	const descriptionTextareaProps: GenericTextareaProps = {
		id: 'description',
		placeholder: 'Descreva seu produto de forma mais detalhada',
		required: false,
		value: description
	};

	const chooseImageProps: ChooseImageProps = {
		label: 'Coloque a imagem do produto',
		value: imageUrl
	};
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
					cancel();
				} else if (result.type === 'redirect') {
					goto(result?.location as string);
				}
			};
		}}
	>
		<input type="hidden" name="id" id="id" value={id} />

		<GenericInput {...nameInputProps} />
		<GenericInput {...shortDescriptionInputProps} />

		<!-- <div class="flex gap-5"> -->
		<GenericInput {...priceInputProps} />
		<!-- <GenericInput {...stockInputProps} /> -->
		<!-- </div> -->

		<hr />

		<GenericTextarea {...descriptionTextareaProps} />

		<ChooseImage {...chooseImageProps} />

		<button class="primary submit-button">{submitButtonLabel}</button>
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
