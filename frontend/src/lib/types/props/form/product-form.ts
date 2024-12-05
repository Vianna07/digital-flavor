import type { ProductDetails } from '$lib/types/data';

export interface ProductFormProps extends Partial<ProductDetails> {
	submitButtonLabel: string;
}
