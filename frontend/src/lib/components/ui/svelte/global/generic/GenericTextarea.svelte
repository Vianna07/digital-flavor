<script lang="ts">
	import type { GenericTextareaProps } from '$lib/types';

	let { id, placeholder, required, oninput, rows = 5, value = "" }: GenericTextareaProps = $props();

	const textareaAtributes = {
		id,
		name: id,
		required,
		placeholder,
		oninput: (event: Event) => oninput?.((event.target as HTMLInputElement).value),
		rows,
		value: value.trim()
	};
</script>

<textarea {...textareaAtributes}></textarea>

<style lang="postcss">
	textarea {
		@apply relative w-full rounded-lg border-2 border-secondary-300 p-4 shadow-md outline-none focus-visible:border-primary;
	}
</style>
