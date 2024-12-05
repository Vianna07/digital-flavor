import { fail, redirect, type Actions, type Cookies } from '@sveltejs/kit';
import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { Product, ProductDetails } from '$lib/types';

export const GET_PRODUCT_BY_ID = async (
	id: string,
	cookies: Cookies,
	fetch: (input: RequestInfo | URL, init?: RequestInit) => Promise<Response>
): Promise<
	| {
			product: ProductDetails;
	  }
	| undefined
> => {
	try {
		const response: Response = await fetch(`${PRIVATE_BACKEND_API_URL}/product/get-by-id/${id}`, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
			}
		});

		const product: ProductDetails = await response.json();

		return {
			product
		};
	} catch (error) {
		console.error(`${new Date()} - Erro ao buscar detalhes do pruduto: ${error}`);
	}
};

export const SAVE_PRODUCT = {
	default: async ({ cookies, request }) => {
		const data = await request.formData();

		const product: Product = {
			name: data.get('name') as string,
			shortDescription: data.get('short-description') as string,
			price: Number(data.get('price')),
			stock: Number(data.get('stock')),
			description: data.get('description') as string,
			imageUrl: data.get('image-url') as string
		};

    if (data.get('id') as string) {
      product.id = data.get('id') as string;
    }

		const file = data.get('image-file') as File;

		try {
			if (product.imageUrl || file.size === 0) {
				await saveOrEditProductWithUrl(product, cookies);
			} else {
				await saveOrEditProductWithFile(product, file, cookies);
			}
		} catch (e) {
			console.error(`${new Date()} - Erro ao salvar produto: ${e}`);
			return fail(400, {
				message: 'Erro ao cadastrar usuário! Tente novamente'
			});
		}

		return redirect(303, '/home');
	}
} satisfies Actions;

async function saveOrEditProductWithUrl(product: Product, cookies: Cookies): Promise<void> {
	const endpoint: string = '/product' + (product?.id ? '/edit' : '/save');
	const method: string = product?.id ? 'PUT' : 'POST';

	await fetch(`${PRIVATE_BACKEND_API_URL}${endpoint}`, {
		method,
		headers: {
			'Content-Type': 'application/json',
			Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
		},
		body: JSON.stringify(product)
	});
}

async function saveOrEditProductWithFile(
	product: Product,
	file: File,
	cookies: Cookies
): Promise<void> {
	const formData = new FormData();
	const endpoint: string = '/product' + (product?.id ? '/edit' : '/save') + '-with-file';
	const method: string = product?.id ? 'PUT' : 'POST';

	formData.append('product', JSON.stringify(product));
	formData.append('file', file);

	await fetch(`${PRIVATE_BACKEND_API_URL}${endpoint}`, {
		method,
		headers: {
			Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
		},
		body: formData
	});
}
