<script lang="ts">
	import type { FormProps } from '$lib/types';

	import LoginForm from '@components/form/LoginForm.svelte';

	const formProps: FormProps = {
		title: 'Entre na sua conta',
		submit: {
			text: 'Faça login',
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

<LoginForm {...formProps} />
