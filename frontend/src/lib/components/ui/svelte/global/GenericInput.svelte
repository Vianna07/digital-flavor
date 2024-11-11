<script lang="ts">
	import type { GenericInputProps } from '$lib/types';

	let {
		id,
		type,
		label,
		leftIconUrl,
		rightIcon,
		pattern,
		required = true,
		oninput
	}: GenericInputProps = $props();

	let isButtonDisabled = $state<boolean>(false);

	function onclick() {
		rightIcon?.onclick();

		isButtonDisabled = true;

		setTimeout(() => {
			isButtonDisabled = false;
		}, 75);
	}
</script>

<section class="form-field">
	<input
		class="form-field__input"
		{type}
		{id}
		name={id}
		placeholder=" "
		{pattern}
		{required}
    oninput={(event: Event) => oninput?.((event.target as HTMLInputElement).value)}
	/>

	{#if leftIconUrl}
		<img src={leftIconUrl} alt="Icon" class="form-field__left-icon" />
	{/if}

	<label class="form-field__label" for={id}>
		{label}
	</label>

	{#if rightIcon}
		<button
			{onclick}
			type="button"
			disabled={isButtonDisabled}
			class="form-field__right-icon"
			tabindex="-1"
		>
			<img src={rightIcon.url} alt="Icon" />
		</button>
	{/if}
</section>

<style lang="postcss">
	.form-field {
		@apply relative bg-secondary-50;

		&__input {
			@apply relative z-10 w-full rounded-lg border-2 border-secondary-300 bg-transparent p-4 shadow-md outline-none focus-visible:border-primary;
		}

		&__input:not(:placeholder-shown) {
			@apply border-primary;
		}

		&__input:focus-visible ~ &__label,
		&__input:not(:placeholder-shown) ~ &__label {
			@apply left-2 top-0 scale-90 bg-gradient-to-b from-light to-secondary-50 text-primary;
		}

		&__input:focus-visible ~ &__left-icon,
		&__input:not(:placeholder-shown) ~ &__left-icon {
			filter: invert(20%) sepia(25%) saturate(100) hue-rotate(15deg);
		}

		&__label {
			@apply absolute left-4 top-1/2 z-10 -translate-y-1/2 rounded-3xl bg-secondary-50 px-2 text-base font-medium text-secondary transition-all;
		}

		&__left-icon {
			@apply absolute left-6 top-1/2 h-5 w-5 -translate-y-1/2;

			:has(&) {
				.form-field__label {
					@apply left-12;
				}

				.form-field__input {
					@apply pl-14;
				}
			}
		}

		&__left-icon,
		&__right-icon {
			filter: invert(20%) sepia(25%) saturate(0) hue-rotate(0);
		}

		&__right-icon {
			@apply absolute right-5 top-1/2 z-10 flex h-5 w-5 -translate-y-1/2 items-center justify-center;

			:has(&) {
				.form-field__input {
					@apply pr-12;
				}
			}
		}
	}
</style>
