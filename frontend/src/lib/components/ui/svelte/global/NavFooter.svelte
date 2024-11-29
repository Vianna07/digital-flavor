<script lang="ts">
	import homeIcon from '@icons/home.svg';
	import personIcon from '@icons/person.svg';
	import bagIcon from '@icons/bag.svg';
	import settingsIcon from '@icons/settings.svg';

	const icons: { icon: string; href: string }[] = [
		{ icon: homeIcon, href: '/home' },
		{ icon: personIcon, href: '/home/customers' },
		{ icon: bagIcon, href: '/home/transactions' },
		{ icon: settingsIcon, href: '/home/settings' }
	];

	let activeIndex = 0;

	function navigateWithAnimation(index: number, href: string) {
		activeIndex = index;
	}
</script>

<footer>
	<nav>
		<ul>
			{#each icons as { icon, href }, i}
				<li class:active={activeIndex === i} class="icon-item">
					<a {href} on:click={() => navigateWithAnimation(i, href)}>
						<span class="icon">
							<img src={icon} alt="icon" />
						</span>
					</a>
				</li>
			{/each}
			<div class="indicator" style="transform: translateX(calc(80px * {activeIndex} - 115px));">
				<span></span>
			</div>
		</ul>
	</nav>
</footer>

<style lang="postcss">
	footer,
	nav {
		@apply flex h-[4.375rem] w-full items-center justify-center bg-primary;
	}

	footer {
		@apply mt-5;

		nav {
			ul {
				@apply relative flex w-full items-center justify-center;

				li {
					@apply relative z-10 h-[4.375rem] w-20 cursor-pointer list-none;

					a {
						@apply relative flex w-full items-center justify-center;

						.icon {
							@apply relative block text-[2em] text-light opacity-85;

							filter: brightness(0) invert(1) sepia(0) saturate(0);
							transition:
								opacity 0.3s,
								transform 0.3s;

							img {
								@apply ml-[0.3125rem] h-[3.75rem] w-[1.875rem];

								transition: transform 0.3s ease;
							}
						}
					}

					&.active {
						a {
							.icon {
								@apply -translate-y-[0.4375rem] opacity-100;

								filter: brightness(0) invert(12%) sepia(82%) saturate(50);
								transition:
									opacity 0.3s,
									transform 0.3s;
							}
						}
					}
				}

				.indicator {
					@apply absolute -top-[0.625rem] h-[4.375rem] w-[4.375rem] cursor-pointer rounded-bl-[2.1875rem] rounded-br-[2.1875rem] border-[6px] border-solid border-light bg-light;

					transition: transform 0.5s ease;

					&::before,
					&::after {
						@apply absolute top-1 h-5 w-5 bg-transparent;

						content: '';
					}

					&::before {
						@apply -left-[1.609375rem] rounded-tr-[1.25rem];

						box-shadow: 4px -6px 0 2px theme('colors.light');
					}

					&::after {
						@apply -right-[1.609375rem] rounded-tl-[1.25rem];

						box-shadow: -4px -6px 0 2px theme('colors.light');
					}

					span {
						@apply absolute -left-[0.1875rem] h-[3.75rem] w-[3.75rem] scale-[0.85] rounded-[50%] border-4 border-solid border-primary;

						box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
					}
				}
			}
		}
	}
</style>
