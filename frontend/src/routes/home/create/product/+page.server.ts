import { fail, redirect, type Cookies } from '@sveltejs/kit';
import type { Actions } from './$types';
import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { Product } from '$lib/types';

export const actions = {
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

		const file = data.get('image-file') as File;

		try {
			if (product.imageUrl || file.size === 0) {
				saveProductWithUrl(product, cookies);
			} else {
				saveProductWithFile(product, file, cookies);
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

async function saveProductWithUrl(product: Product, cookies: Cookies): Promise<void> {
	await fetch(`${PRIVATE_BACKEND_API_URL}/product/save`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
		},
		body: JSON.stringify(product)
	});
}

async function saveProductWithFile(product: Product, file: File, cookies: Cookies): Promise<void> {
	const formData = new FormData();

	formData.append('product', JSON.stringify(product));
	formData.append('file', file);

	await fetch(`${PRIVATE_BACKEND_API_URL}/product/save-with-file`, {
		method: 'POST',
		headers: {
			Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
		},
		body: formData
	});
}
