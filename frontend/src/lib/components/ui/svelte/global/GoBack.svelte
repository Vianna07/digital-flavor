<script lang="ts">
	import type { GoBackProps } from '$lib/types';
	import keyboardArrowLeft from '@icons/keyboard-arrow-left.svg';

	let { iconUrl, redirectUrl }: GoBackProps = $props();
</script>

<a href={redirectUrl}>
	<button type="button" class="primary">
		<img src={iconUrl ?? keyboardArrowLeft} alt="Arrow-left" />
	</button>
</a>
