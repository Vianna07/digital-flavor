import type { Snippet } from 'svelte';

export interface GenericListProps<T> {
	data: T[];
	fields: {
		title: keyof T;
		subTitle: keyof T;
	};
	left?: {
		snippet: Snippet<[field: T[keyof T]]>;
		field: keyof T;
	};
	onclick?: (id: string) => void;
	noDataMessage?: string;
}
