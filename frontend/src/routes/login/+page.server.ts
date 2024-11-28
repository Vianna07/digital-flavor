import { fail, redirect } from '@sveltejs/kit';
import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { Actions } from './$types';

export const actions = {
	default: async ({ cookies, request }) => {
		const data = await request.formData();

		const email = data.get('email');
		const password = data.get('password');

		try {
			const response: Response = await fetch(`${PRIVATE_BACKEND_API_URL}/security/login`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ email: email, password: password })
			});

			if (!response.ok) {
				return fail(400, {
					message: 'E-mail ou senha inválidos!'
				});
			}

			const user = await response.json();
			cookies.set('user', JSON.stringify(user), {
				path: '/',
				maxAge: 31536000,
				secure: false,
				httpOnly: true,
				sameSite: 'strict'
			});
		} catch (e) {
			console.error(`${new Date()} - Error: ${e}`);

			return fail(400, {
				message: 'Erro ao logar! Tente novamente'
			});
		}

		return redirect(303, '/login/canteen');
	}
} satisfies Actions;
