import { PUBLIC_API_URL } from '$env/static/public';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ fetch }) => {
	try {
		const response: Response = await fetch(`${PUBLIC_API_URL}/canteen/get-all`, {
			method: 'GET'
		});

		const canteens = await response.json();

		return {
			canteens: canteens
		};
	} catch (error) {
		console.error(`${new Date()} - Error: ${error}`);
	}
};
