type UserType = 'admin' | 'canteen' | 'client';

export interface LoginRequest {
	email: string;
	password: string;
	canteenId?: string;
}

export interface User {
	name: string;
	email: string;
	password: string;
	userType: UserType;
}
