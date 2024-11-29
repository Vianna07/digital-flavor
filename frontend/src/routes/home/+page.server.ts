import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import { UserType, type Canteen, type ProductCardProps } from '$lib/types';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ cookies, fetch }) => {
	try {
    const requestInit: RequestInit = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: JSON.parse(cookies.get('authorization') || '{}') as string,
      },
    }

    const [productResponse, userTypeResponse, canteenResponse] = await Promise.all([
      fetch(`${PRIVATE_BACKEND_API_URL}/product/get-all`, {
        ...requestInit
      }),
      fetch(`${PRIVATE_BACKEND_API_URL}/user/get-user-type`, {
        ...requestInit
      }),
      fetch(`${PRIVATE_BACKEND_API_URL}/canteen/get-by-id`, {
        ...requestInit
      })
    ]);

    if (!productResponse.ok || !userTypeResponse.ok) {
      throw new Error('Falha ao buscar dados do servidor.');
    }

    const [products, userType, canteen] = await Promise.all([
      productResponse.json(),
      userTypeResponse.text(),
      canteenResponse.json(),
    ]);

    return {
      products: products as ProductCardProps[],
      userType: UserType[userType as keyof typeof UserType],
      canteen: canteen as Canteen
    };
	} catch (error) {
		console.error(`${new Date()} - Erro: ${error}`);
	}
};
