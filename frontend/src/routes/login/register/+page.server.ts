import { fail, redirect } from '@sveltejs/kit';
import type { Actions } from '../$types';
import { PUBLIC_API_URL } from '$env/static/public';

export const actions = {
	default: async ({ cookies, request }) => {
		const data = await request.formData();

		const name = data.get('name');
		const email = data.get('email');
		const password = data.get('password');
		const retypePassword = data.get('retypePassword');

		if (password !== retypePassword) {
			return fail(400, {
				message: 'As senhas são diferentes!'
			});
		}

		try {
			const response: Response = await fetch(`${PUBLIC_API_URL}/user/save`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ name: name, email: email, password: password })
			});

			if (!response.ok) {
				return fail(400, {
					message: 'Usuário já cadastrado!'
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
				message: 'Erro ao cadastrar usuário! Tente novamente'
			});
		}

		return redirect(303, '/login/canteen');
	}
} satisfies Actions;
