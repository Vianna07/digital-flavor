import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { ListingOrder } from '$lib/types';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ cookies, fetch }) => {
  try {
    const response: Response = await fetch(`${PRIVATE_BACKEND_API_URL}/order/get-all`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
      }
    });

    const orders: ListingOrder[] = await response.json();

    return {
      orders
    };
  } catch (error) {
    console.error(`${new Date()} - Erro ao buscar detalhes do pruduto: ${error}`);
  }
};
