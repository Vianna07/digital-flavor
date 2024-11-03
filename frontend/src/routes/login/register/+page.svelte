<script lang="ts">
	import type { FormProps } from '$lib/types';

	import Form from '@components/form/Form.svelte';

	const formProps: FormProps = {
		title: 'Crie sua conta',
		submit: {
			text: 'Cadastre-se',
			paragraph: {
				text: 'Já possui uma conta?',
				link: {
					text: 'Faça login',
					href: '/login'
				}
			}
		},
		inputs: {
			name: true,
			confirmPassword: true
		}
	};
</script>

<Form {...formProps} />
