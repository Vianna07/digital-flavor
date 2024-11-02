import { fail, redirect } from '@sveltejs/kit';
import type { Actions } from '../$types';
import { PUBLIC_API_URL } from '$env/static/public';

export const actions = {
	default: async ({ request }) => {
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
				throw 'Erro ao cadastrar o usuário: ' + response.statusText;
			}

      const user = await response.json();

      document.cookie = `user=${JSON.stringify(user)}; path=/; max-age=31536000; Secure; HttpOnly; SameSite=Strict;`;

      return redirect(303, '/login/canteen');
		} catch (e) {
			console.error(`${new Date()} - Error: ${e}`);

			return fail(400, {
				message: 'Erro ao cadastrar usuário! Tente novamente'
			});
		}
	}
} satisfies Actions;
