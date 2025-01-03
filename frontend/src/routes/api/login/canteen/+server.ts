import { error, json, type Cookies, type RequestEvent } from '@sveltejs/kit';
import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { Canteen, LoginRequest, Token, User } from '$lib/types';

export const POST = async ({ request, cookies }: RequestEvent) => {
	try {
		const { canteenId, nameOrAddress }: { canteenId: string; nameOrAddress: string } =
			await request.json();

		const fn = canteenId
			? loginCanteen.bind(null, canteenId, cookies)
			: getAllByNameOrAddress.bind(null, nameOrAddress);

		return fn();
	} catch (err) {
		const message: string = 'Erro ao logar na cantina';

		console.error(`${new Date()} - ${message}: ${err}`);

		return error(500, `${message}!`);
	}
};

async function loginCanteen(canteenId: string, cookies: Cookies) {
	const user: User = JSON.parse(cookies.get('user') ?? '{}');

	if (!canteenId || !user || !user.email || !user.password) {
		throw error(400, 'Dados inválidos');
	}

	const loginRequest: LoginRequest = {
		email: user.email,
		password: user.password,
		canteenId
	};

	const response = await fetch(`${PRIVATE_BACKEND_API_URL}/security/login-canteen`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(loginRequest)
	});

	if (!response.ok) {
		throw error(500, 'Falha ao obter token');
	}

	const { authorization, expiresAt }: Token = await response.json();

	cookies.delete('user', { path: '/' });
	cookies.set('authorization', JSON.stringify(authorization), {
		path: '/',
		maxAge: expiresAt,
		secure: false,
		httpOnly: true,
		sameSite: 'strict'
	});

	return json({ authorization, expiresAt }, { status: 200 });
}

async function getAllByNameOrAddress(nameOrAddress: string): Promise<Response> {
	const response: Response = await fetch(
		`${PRIVATE_BACKEND_API_URL}/canteen/get-all-by-name-or-address/${nameOrAddress}`,
		{
			method: 'GET'
		}
	);

	const canteens: Canteen[] = await response.json();

	return json(canteens, { status: 200 });
}
