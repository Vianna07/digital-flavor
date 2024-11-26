<script lang="ts">
	import type { Customer, GenericListProps, GenericSearchableListProps, InputProps } from "$lib/types";
	import GenericSearchableList from "@components/global/generic/GenericSearchableList.svelte";
  import { page } from '$app/stores';

  let { data }: { data: { customers: Customer[] }} = $props()

  const FETCH_URL = `/api${$page.url.pathname}`;

  let customers: GenericListProps<Customer> = $state({
		data: data.customers,
		fields: {
			title: 'name',
			subTitle: 'email'
		},
		noDataMessage: 'Nenhum cliente encontrado'
	});

	const searchInput: InputProps = {
		label: 'Pesquise pelo cliente',
		oninput: async (nameOrEmail: string) => {
			try {
				if (nameOrEmail) {
					const response: Response = await fetch(FETCH_URL, {
						method: 'POST',
						headers: {
							'Content-Type': 'application/json'
						},
						body: JSON.stringify({ nameOrEmail: nameOrEmail })
					});

					customers.data = await response.json();
				}
			} catch (error) {
				console.error(error);
			}
		}
	};

  const searchableList: GenericSearchableListProps<Customer> = {
    list: customers,
    searchInput: searchInput,
    style: 'gap-10'
  }
</script>

<section class="page">
  <header>
    <h1 class="simple-title">Clientes</h1>
  </header>

  <GenericSearchableList {...searchableList} />
</section>

<style lang="postcss"></style>
