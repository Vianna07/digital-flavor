import type { Snippet } from 'svelte';

type ListingType = 'open-listing' | 'closed-listing' | 'custom-listing';

export interface GenericListProps<T> {
	listingType?: ListingType;
	data?: T[];
	fields?: {
		title: keyof T;
		subTitle?: keyof T;
	};
	left?: {
		snippet: Snippet<[index: number, field: T[keyof T]]>;
		field?: keyof T;
	};
  right?: {
		snippet: Snippet<[index: number, field: T[keyof T]]>;
		field?: keyof T;
	};
	onclick?: (id: string) => void;
	noDataMessage?: string;
	customListing?: Snippet<[T[] | undefined]>;
  contentStyle?: {
    content?: string
    title?: string
    subtitle?: string
  }
}
