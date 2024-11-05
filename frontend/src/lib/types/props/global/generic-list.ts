import type { Snippet } from 'svelte';

export interface GenericListProps<T> {
	list: T[];
	fields: {
		title: keyof T;
		subTitle: keyof T;
	};
	left?: Snippet;
	onclick?: (id: string) => void;
}
