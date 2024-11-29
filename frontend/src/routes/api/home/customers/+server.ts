import { error, json, type RequestEvent } from '@sveltejs/kit';
import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { Customer } from '$lib/types';

export const POST = async ({ request, cookies }: RequestEvent) => {
	try {
		const { nameOrEmail }: { nameOrEmail: string } = await request.json();
		const response: Response = await fetch(
			`${PRIVATE_BACKEND_API_URL}/user/get-all-customers/${nameOrEmail}`,
			{
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
					Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
				}
			}
		);

		const customers: Customer[] = await response.json();

		return json(customers, { status: 200 });
	} catch (err) {
		const message: string = 'Erro ao buscar clientes';

		console.error(`${new Date()} - ${message}: ${err}`);

		return error(500, `${message}!`);
	}
};
