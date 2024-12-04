import { GET_PRODUCT_BY_ID } from '@services/product';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ params, cookies, fetch }) => {
	return GET_PRODUCT_BY_ID(params.id, cookies, fetch);
};
