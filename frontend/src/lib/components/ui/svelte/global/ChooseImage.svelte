<script lang="ts">
	import uploadFileIcon from '@icons/upload_file.svg';
	import linkIcon from '@icons/link.svg';
	import attachFile from '@icons/attach_file.svg';
	import closeIcon from '@icons/close.svg';
	import GenericInput from '@components/global/generic/GenericInput.svelte';
	import Modal from './Modal.svelte';
	import type { GenericInputProps, ModalProps } from '$lib/types';

	let { label }: { label: string } = $props();

	let showUploadButton = $state(true);
	let isUrlOption = $state(false);

	let fileInput = $state() as HTMLInputElement;
	let previewUrl = $state('');
	let url: string | null = $state(null);

	let modalProps: ModalProps = $state({
		isOpen: false,
		title: 'Adicione a URL'
	});

	const urlInputProps: GenericInputProps = {
		id: 'url-input',
		type: 'text',
		required: false,
		leftIconUrl: linkIcon,
		label: 'Digite a URL da imagem',
		oninput: (value: string) => {
			previewUrl = url = value;
		}
	};

	let options: { label: string; action: () => void }[] = [
		{
			label: 'Adicionar URL',
			action: () => {
				showUploadButton = false;
				isUrlOption = true;

				modalProps = { isOpen: true, title: 'Adicione a URL' };
			}
		},
		{
			label: 'Adicionar arquivo',
			action: () => {
				showUploadButton = false;
				isUrlOption = false;

				modalProps = { isOpen: true, title: 'Adicione o arquivo' };
			}
		}
	];

	async function onclick() {
		showUploadButton = false;
		isUrlOption = false;
	}

	function onChangeFileInput(event: Event) {
		const files = (event.target as HTMLInputElement).files || null;

		if (!files) {
			return;
		}

		fileInput.files = files;
		url = URL.createObjectURL(files[0]);
	}
</script>

{#snippet modal()}
	<div class="image-preview">
		<img src={url} alt="Preview da Imagem" class="" />
	</div>

	{#if isUrlOption}
		<GenericInput {...urlInputProps} />
	{:else}
		<div class="file-input">
			<label class="primary" for="file-image-input">
				<img src={attachFile} alt="attach-file-icon" />
				<span> Escolha a imagem </span>
			</label>

			<input id="file-image-input" type="file" accept=".png" onchange={onChangeFileInput} />
		</div>
	{/if}
{/snippet}

{#snippet closeButton(onclick: () => void)}
	<button type="button" class="close-button" {onclick}>
		<img src={closeIcon} alt="closeIcon" />
	</button>
{/snippet}

<div class="choose-image-container">
	<h1 class="title">{label}</h1>

	<div class="image-preview">
		{#if showUploadButton && !url}
			<button {onclick} type="button" class="upload-button">
				<img src={uploadFileIcon} alt="" />
			</button>
		{/if}

		{#if !showUploadButton && !url}
			{@render closeButton(() => {
				showUploadButton = true;
			})}
			{#each options as { label, action }}
				<button type="button" class="primary upload-option" onclick={action}>
					{label}
				</button>
			{/each}
		{/if}

		{#if url}
			{@render closeButton(() => {
				fileInput.value = '';
				previewUrl = '';
				url = '';
				showUploadButton = true;
			})}
			<img src={url} alt="" />
		{/if}

		<input id="image-url" name="image-url" class="hidden" type="text" bind:value={previewUrl} />

		<input
			id="image-file"
			name="image-file"
			class="hidden"
			type="file"
			accept=".png"
			bind:this={fileInput}
		/>

		<Modal {...modalProps}>
			{@render modal()}
		</Modal>
	</div>
</div>

<style lang="postcss">
	img {
		@apply max-h-56 max-w-64;
	}

	.choose-image-container {
		@apply flex flex-col items-center gap-1;

		& h1.title {
			@apply text-secondary-600;
		}
	}

	.image-preview {
		@apply relative flex h-56 w-64 flex-col items-center justify-center gap-10 rounded-lg border-4 border-secondary bg-secondary-300;

		.upload-button {
			@apply flex h-full w-full items-center justify-center;

			img {
				@apply h-16 w-16;
			}
		}

		.upload-option {
			@apply w-10/12;
		}
	}

	.file-input {
		@apply w-full;

		label {
			@apply flex;

			span {
				@apply w-full text-center;
			}
		}

		input {
			@apply hidden;
		}
	}

	.close-button {
		@apply absolute right-1.5 top-1.5 rounded-lg bg-secondary-50;

		img {
			@apply h-5 w-5;
		}
	}
</style>
