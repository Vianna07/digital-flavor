import { GET_PRODUCT_BY_ID, SAVE_PRODUCT } from '@services/product';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ params, cookies, fetch }) => {
	return GET_PRODUCT_BY_ID(params.id, cookies, fetch);
};

export const actions = SAVE_PRODUCT;