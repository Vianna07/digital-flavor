import type { Snippet } from 'svelte';

type ListingType = 'open-listing' | 'closed-listing';

export interface GenericListProps<T> {
	listingType?: ListingType;
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
