import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ fetch }) => {
	try {
		const response: Response = await fetch(`${PRIVATE_BACKEND_API_URL}/canteen/get-all`, {
			method: 'GET'
		});

		const canteens = await response.json();

		return {
			canteens: canteens
		};
	} catch (error) {
		console.error(`${new Date()} - Erro ao buscar cantinas: ${error}`);
	}
};
