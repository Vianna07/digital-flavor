<script lang="ts">
	import logo from '@images/logo.png';
	import GenericList from '@components/global/GenericList.svelte';
	import GenericInput from '@components/global/GenericInput.svelte';
	import searchIcon from '@icons/magnifying-glass.svg';
	import noImageIcon from '@icons/no_photography.svg';
	import type { Canteen, GenericInputProps, GenericListProps } from '$lib/types';
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';

	let { data }: { data: { canteens: Canteen[] } } = $props();

  const FETCH_URL = `/api${$page.url.pathname}`

  console.log(FETCH_URL);


	let canteens: GenericListProps<Canteen> = $state({
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
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({canteenId: id}),
        });

        goto('/home')

      } catch (error) {
        console.error(error);
      }
		},
    noDataMessage: 'Nenhuma cantina encontrada'
	});

	const searchInput: GenericInputProps = {
		id: 'search',
		type: 'text',
		label: 'Pesquise pela cantina',
		leftIconUrl: searchIcon,
    oninput: async(nameOrAddress: string) => {
      try {
        if (nameOrAddress) {
          const response: Response = await fetch(FETCH_URL, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({canteenId: '', nameOrAddress}),
          });

          canteens.data = await response.json();
        }
      } catch (error) {
        console.error(error);
      }
    }
	};
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

	<div class="content">
		<GenericInput {...searchInput} />
		<GenericList {...canteens} />
	</div>

	<footer>
		<p>Selecione a loja que deseja comprar conforme a sua localização</p>
	</footer>
</section>

<style lang="postcss">
	.page {
		@apply flex flex-col items-center justify-center gap-10;

		width: clamp(18rem, 20rem, 24rem);

		header {
			@apply absolute top-10 flex items-center justify-center;

			img {
				@apply h-48 w-48;
			}
		}

		.content {
			@apply flex w-full max-w-full flex-col gap-10 h-96;
		}

		footer {
			@apply absolute bottom-10 max-w-full px-10 text-center;
		}
	}
</style>
