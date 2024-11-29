<script lang="ts">
	import { goto } from '$app/navigation';
	import type { UserSettings } from '$lib/types';
	import logoutIcon from '@icons/logout.svg';
	import settingsIcon from '@icons/settings.svg';

	let { data }: { data: { userSettings: UserSettings } } = $props();
	const user = data.userSettings;

	function logout() {
		goto('/');
	}
</script>

<div class="page">
	<header>
		<h1>Perfil</h1>
	</header>

	<div class="content">
		<div class="user-info">
			<h2>{user.name}</h2>
			<p class="user-email">{user.email}</p>
		</div>

		<div class="actions">
			<button class="action">
				<img src={settingsIcon} alt="Configuração" class="icon" />
				<p>Editar informações</p>
			</button>
			<button class="action" onclick={logout}>
				<img src={logoutIcon} alt="Logout" class="icon" />
				<p>Logout</p>
			</button>
		</div>
	</div>

  <footer>
		<p>Digital Flavor</p>
	</footer>
</div>

<style lang="postcss">
	.page {
		header {
			@apply mb-8 mt-6 flex w-full justify-center;

			h1 {
				@apply text-center text-2xl font-bold text-primary;
			}
		}

		.content {
			@apply flex flex-col items-center gap-8;

			.user-info {
				@apply text-center;

				h2 {
					@apply text-xl font-semibold text-contrast;
				}

				p {
					@apply text-sm text-placeholder;
				}

				.user-email {
					@apply text-primary;
				}
			}

			.actions {
				@apply flex flex-col gap-6;

				.action {
					@apply flex items-center gap-4;

					transition: 200ms ease-in-out all;

					&:hover {
						@apply scale-110;
					}

					.icon {
						filter: invert(20%) sepia(25%) saturate(100) hue-rotate(15deg);
					}

					p {
						@apply text-base text-contrast;
					}
				}
			}
		}

    footer {
			@apply mt-auto text-primary text-lg font-bold;
		}
	}
</style>
