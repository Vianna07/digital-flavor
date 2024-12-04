<script lang="ts">
	import type { LoginFormProps } from '$lib/types';

	import LoginForm from '@components/form/LoginForm.svelte';

	const formProps: LoginFormProps = {
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
