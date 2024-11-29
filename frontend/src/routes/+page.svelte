<script>
	import logo from '@images/logo.png';
	import { goto } from '$app/navigation';
</script>

<div class="center">
	<div class="page">
		<img src={logo} alt="Digital Flavor Logo" />
		<h1>Bem-vindo ao Digital Flavor</h1>

		<button class="primary" onclick={() => goto('/login')}>Entrar</button>
		<button class="sign-up" onclick={() => goto('/login/register')}>Cadastrar</button>
	</div>
</div>

<style lang="postcss">
	.page {
		@apply gap-8;

		img {
			@apply mb-4 h-56 w-56;
		}

		h1 {
			@apply text-center text-4xl font-bold text-primary;
		}

		button {
			@apply w-full;
		}

		.sign-up {
			@apply rounded-xl border-4 border-secondary-400 bg-secondary-200 p-2.5 text-primary outline-none drop-shadow-lg transition-colors duration-[var(--transition-speed)];

			&:hover,
			&:focus-visible {
				@apply bg-secondary-300;
			}
		}
	}
</style>
