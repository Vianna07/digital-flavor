import type { Snippet } from 'svelte';

export interface GenericListProps<T> {
	list: T[];
	fields: {
		title: keyof T;
		subTitle: keyof T;
	};
	left?: {
    snippet: Snippet<[ field: keyof T ]>;
    field: keyof T;
  };
	onclick?: (id: string) => void;
}
