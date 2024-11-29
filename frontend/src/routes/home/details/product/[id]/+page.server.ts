import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { ProductDetails } from '$lib/types';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ params, cookies, fetch }) => {
	try {
		const response: Response = await fetch(
			`${PRIVATE_BACKEND_API_URL}/product/get-by-id/${params.id}`,
			{
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
					Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
				}
			}
		);

		const product: ProductDetails = await response.json();

		return {
			product
		};
	} catch (error) {
		console.error(`${new Date()} - Erro ao buscar detalhes do pruduto: ${error}`);
	}
};
