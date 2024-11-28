<script lang="ts">
	import closeIcon from '@icons/close.svg';
	import type { ModalProps } from '$lib/types';
	import { scale } from 'svelte/transition';

	let { children, isOpen, title }: ModalProps = $props();

	const closeModal = () => (isOpen = false);
</script>

{#if isOpen}
	<div class="screen">
		<div class="screen__modal" transition:scale={{ start: 0.5, duration: 250 }}>
			<header>
				<h1>{title}</h1>

				<button type="button" onclick={closeModal}>
					<img src={closeIcon} alt="closeIcon" />
				</button>
			</header>

			{@render children?.()}
		</div>
	</div>
{/if}

<style class="postcss">
	.screen {
		@apply fixed left-0 top-0 z-50 flex h-screen w-screen items-center justify-center bg-black bg-opacity-50;

		&__modal {
			@apply flex min-h-56 w-80 flex-col items-center gap-5 rounded-lg bg-white p-6 shadow-lg;

			header {
				@apply flex justify-between;

				h1 {
					@apply text-xl font-bold;
				}
			}
		}
	}
</style>
