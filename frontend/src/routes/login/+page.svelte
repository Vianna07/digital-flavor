<script lang="ts">
	import type { FormProps } from '$lib/types';

	import Form from '@components/form/Form.svelte';

	const formProps: FormProps = {
		title: 'Entre na sua conta',
		submit: {
			text: 'Cadastre-se',
			paragraph: {
				text: 'Não possui uma conta?',
				link: {
					text: 'Cadastre-se',
					href: '/login/register'
				}
			}
		}
	};
</script>

<Form {...formProps} />
