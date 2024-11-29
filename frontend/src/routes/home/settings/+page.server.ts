import { PRIVATE_BACKEND_API_URL } from '$env/static/private';
import type { UserSettings } from '$lib/types';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ cookies, fetch }) => {
	try {
		const response: Response = await fetch(
			`${PRIVATE_BACKEND_API_URL}/user/get-user-settings-by-id`,
			{
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
					Authorization: JSON.parse(cookies.get('authorization') || '{}') as string
				}
			}
		);

		const userSettings: UserSettings = await response.json();

		return {
			userSettings: userSettings
		};
	} catch (error) {
		console.error(
			`${new Date()} - Erro ao buscar informações de configuração do usuário: ${error}`
		);
	}
};
