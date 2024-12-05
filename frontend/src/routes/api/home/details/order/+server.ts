import { error, json, type RequestEvent } from '@sveltejs/kit';
import { PRIVATE_BACKEND_API_URL } from '$env/static/private';

export const POST = async ({ request, cookies }: RequestEvent) => {
	try {
		const { orderItems, paymentMethod }: { orderItems: string, paymentMethod: string } = await request.json();

    const body = orderItems
    ? JSON.stringify({ orderItemsDto: (JSON.parse(orderItems) as Array<[string, {name: string, price: number, quantity: number}]>)
    .map(([id, obj]: [id: string, {name: string, price: number, quantity: number}]) => ({productId: id, price: obj.price, quantity: obj.quantity})),
    paymentMethod})
    : undefined;

		await fetch(
			`${PRIVATE_BACKEND_API_URL}/order/save`,
			{
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
				},
        body
			}
		);

		return json(null, { status: 200 });
	} catch (err) {
		const message: string = 'Erro ao salvar pedido';

		console.error(`${new Date()} - ${message}: ${err}`);

		return error(500, `${message}!`);
	}
};
