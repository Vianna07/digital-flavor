<script lang="ts">
	import type { LoginFormProps } from '$lib/types';

	import LoginForm from '@components/form/LoginForm.svelte';

	const formProps: LoginFormProps = {
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

<LoginForm {...formProps} />
