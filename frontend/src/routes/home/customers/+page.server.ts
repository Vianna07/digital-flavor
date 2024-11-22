import { PUBLIC_API_URL } from '$env/static/public';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ fetch, cookies }) => {
	try {
		const response: Response = await fetch(`${PUBLIC_API_URL}/user/get-all-customers`, {
			method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': JSON.parse(cookies.get('authorization') || '{}') as string
      }
		});

		const customers = await response.json();

		return {
			customers: customers
		};
	} catch (error) {
		console.error(`${new Date()} - Erro ao buscar clientes: ${error}`);
	}
};
