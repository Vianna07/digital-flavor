import { error, json, type RequestEvent } from '@sveltejs/kit';
import { PUBLIC_API_URL } from '$env/static/public';
import type { LoginRequest, User } from '$lib/types';

export const POST = async ({ request, cookies }: RequestEvent) => {
  try {
    const { canteenId }: { canteenId: string } = await request.json();

    const user: User = JSON.parse(cookies.get('user') ?? '');

    if (!canteenId || !user || !user.email || !user.password) {
      throw error(400, 'Dados inválidos');
    }

    const loginRequest: LoginRequest = {
      email: user.email,
      password: user.password,
      canteenId
    };

    const response = await fetch(`${PUBLIC_API_URL}/security/login-canteen`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginRequest)
    });

    if (!response.ok) {
      throw error(500, 'Falha ao obter token');
    }

    const data: { authorization: string, expiresAt: number } = await response.json();

    cookies.delete('user', { path: '/' })
    cookies.set('access_token', JSON.stringify(data.authorization), {
      path: '/',
      maxAge: data.expiresAt,
      secure: false,
      httpOnly: true,
      sameSite:'strict'
    })

    return json(data, { status: 200 });

  } catch (err) {
    const message: string = 'Erro ao logar na cantina';

    console.error(`${new Date()} - ${message}: ${err}`);

    return error(500, `${message}!`)
  }
};
