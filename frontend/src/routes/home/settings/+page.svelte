<script lang="ts">
	import logoutIcon from '@icons/logout.svg'
	import settingsIcon from '@icons/settings.svg'
	let user = {
		name: "Seu Nome",
		email: "seu.email@mail.com"
	};
</script>

<div class="profile-page">
	<header>
		<h1>Perfil</h1>
	</header>

	<div class="content">
		<div class="user-info">
			<h2>{user.name}</h2>
			<p class="user-email">{user.email}</p>
		</div>

		<div class="actions">
			<div class="action">
				<img src={settingsIcon} alt="Configuração" class="icon" />
				<p>Editar informações</p>
			</div>
			<div class="action">
				<img src={logoutIcon} alt="Logout" class="icon" />
				<p>Logout</p>
			</div>
		</div>
	</div>

	<footer>
		<p>Digital Flavor</p>
	</footer>
</div>

<style lang="postcss">
	.profile-page {
		@apply flex flex-col items-center min-h-screen bg-light text-contrast p-5;

		header {
			@apply mt-6 mb-8 w-full flex justify-center;

			h1 {
				@apply text-2xl font-bold text-primary text-center;
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
