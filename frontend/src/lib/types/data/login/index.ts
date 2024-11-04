enum UserType {
	ADMIN = 1,
	MANAGER = 2,
	EMPLOYEE = 3,
	CUSTOMER = 4
}

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
