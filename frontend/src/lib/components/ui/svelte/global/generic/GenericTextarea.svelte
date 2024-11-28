<script lang="ts">
	import type { GenericTextareaProps } from "$lib/types";

	let {
		id,
    placeholder,
		required,
		oninput,
    rows = 5,
	}: GenericTextareaProps = $props();
</script>

<textarea
  {id}
  name={id}
  {required}
  {placeholder}
  oninput={(event: Event) => oninput?.((event.target as HTMLInputElement).value)}
  rows={rows}
>
</textarea>

<style lang="postcss">
  textarea {
    @apply relative z-10 w-full rounded-lg border-2 border-secondary-300 outline-none p-4 shadow-md focus-visible:border-primary;
  }
</style>
