import { error, json, type RequestEvent } from '@sveltejs/kit';
import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { ProductCardProps } from '$lib/types';

export const POST = async ({ request, cookies }: RequestEvent) => {
	try {
		const { name }: { name: string } = await request.json();

		const response: Response = await fetch(
			`${PRIVATE_BACKEND_API_URL}/product/get-all-by-name/${name}`,
			{
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
					Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
				}
			}
		);

		const products: ProductCardProps[] = await response.json();

		return json(products, { status: 200 });
	} catch (err) {
		const message: string = 'Erro ao buscar produtos';

		console.error(`${new Date()} - ${message}: ${err}`);

		return error(500, `${message}!`);
	}
};
