export interface Canteen {
	id: string;
	name: string;
	address: string;
	logoUrl?: string;
}

export interface Token {
	authorization: string;
	expiresAt: number;
}
