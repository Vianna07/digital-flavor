<script lang="ts">
	import logo from '@images/logo.png';
	import noImageIcon from '@icons/no_photography.svg';
	import type { Canteen, GenericListProps, GenericSearchableListProps, InputProps } from '$lib/types';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';
	import GenericSearchableList from '@components/global/generic/GenericSearchableList.svelte';

	let { data }: { data: { canteens: Canteen[] } } = $props();

	const FETCH_URL = `/api${$page.url.pathname}`;

	let canteens: GenericListProps<Canteen> = $state({
    listingType: 'closed-listing',
		data: data.canteens,
		fields: {
			title: 'name',
			subTitle: 'address'
		},
		left: {
			snippet: canteenIImage,
			field: 'logoUrl'
		},
		onclick: async (id: string) => {
			try {
				await fetch(FETCH_URL, {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({ canteenId: id })
				});

				goto('/home');
			} catch (error) {
				console.error(error);
			}
		},
		noDataMessage: 'Nenhuma cantina encontrada'
	});

	const searchInput: InputProps = {
		label: 'Pesquise pela cantina',
		oninput: async (nameOrAddress: string) => {
			try {
				if (nameOrAddress) {
					const response: Response = await fetch(FETCH_URL, {
						method: 'POST',
						headers: {
							'Content-Type': 'application/json'
						},
						body: JSON.stringify({ canteenId: '', nameOrAddress })
					});

					canteens.data = await response.json();
				}
			} catch (error) {
				console.error(error);
			}
		}
	};

  const searchableList: GenericSearchableListProps<Canteen> = {
    list: canteens,
    searchInput: searchInput,
    style: 'h-96 gap-10'
  }
</script>

{#snippet canteenIImage(url?: string)}
	{@const src: string = url ?? noImageIcon}
	{@const area: string = src === noImageIcon ? 'w-10 h-10' : 'w-16 h-16'}
	<img class={area} {src} alt="logo" />
{/snippet}

<section class="page">
	<header>
		<img src={logo} alt="logo" />
	</header>

  <GenericSearchableList {...searchableList} />

	<footer>
		<p>Selecione a loja que deseja comprar conforme a sua localização</p>
	</footer>
</section>

<style lang="postcss">
	.page {
		@apply h-full my-4 flex flex-col items-center justify-around gap-6;

		width: clamp(18rem, 20rem, 24rem);

		header {
			@apply flex items-center justify-center;

			img {
				@apply h-32 w-32;
			}
		}

		footer {
			@apply text-center;
		}
	}
</style>
