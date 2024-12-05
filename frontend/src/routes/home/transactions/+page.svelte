<script lang="ts">
	import type { GenericSearchableListProps, ListingOrder } from "$lib/types";
	import GenericSearchableList from "@components/global/generic/GenericSearchableList.svelte";
	import GoBack from "@components/global/GoBack.svelte";

  let { data }: { data: { orders: ListingOrder[] } } = $props()

	// let tabs = ["Pedidos", "Pagamentos"];
	// let activeTab = $state(tabs[0]);

  const searchableListProps: GenericSearchableListProps<ListingOrder> = {
    list: {
      data: data.orders,
      fields: {
        title: 'userName',
        subTitle: 'createdAt'
      },
      contentStyle: {
        title: 'text-left',
        subtitle: 'text-left'
      },

      right: {
        snippet: right,
        field: 'value'
      }
    },
    searchInput: {
      label: "Buscar pedido por cliente",
    }
  }

	// function switchTab(tab: string) {
	// 	activeTab = tab;
	// }
</script>

{#snippet right(index: number)}
  <p class="order-value">{(data.orders[index].value).toLocaleString('pt-br', {
    style: 'currency',
    currency: 'BRL'
  })}</p>
{/snippet}

<div class="page">
	<header>
		<h1 class="simple-title">Transações</h1>
	</header>

	<!-- <div class="tabs">
		{#each tabs as tab}
			<button
				class="tab {activeTab === tab ? 'active' : ''}"
				onclick={() => switchTab(tab)}
			>
				{tab}
			</button>
		{/each}
	</div> -->

  <GenericSearchableList {...searchableListProps} />
</div>

<style lang="postcss">
  .order-value {
    @apply w-32 text-primary font-bold;
  }

	.page {
		@apply flex flex-col items-center gap-6 font-body;

		.tabs {
			@apply flex justify-between gap-4 mb-6 w-full max-w-md;

			.tab {
				@apply flex-1 px-6 py-3 rounded-full bg-secondary text-secondary-50 text-sm font-semibold text-center shadow-md transition duration-speed;
				&.active {
					@apply bg-primary text-light shadow-lg;
				}
			}
		}
	}
</style>
