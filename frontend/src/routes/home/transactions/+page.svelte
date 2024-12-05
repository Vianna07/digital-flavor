<script lang="ts">
	import GoBack from "@components/global/GoBack.svelte";

	let tabs = ["Pedidos", "Pagamentos"];
	let activeTab = tabs[0];

	let transactions = [
		{ name: "Giuseppe Cadura", date: "26/11/2024 - 10:05", amount: 12.50 },
		{ name: "Mia Regarza", date: "25/11/2024 - 9:40", amount: 10.00 },
		{ name: "Sintia Miavara", date: "25/11/2024 - 9:39", amount: 8.00 },
		{ name: "Koy Tannal", date: "25/11/2024 - 9:39", amount: 2.50 },
		{ name: "Kiko Lindo", date: "25/11/2024 - 9:38", amount: 5.00 }
	];

	function switchTab(tab: string) {
		activeTab = tab;
	}
</script>

<div class="page">
	<header>
		<GoBack redirectUrl={'/home'} />
		<h1 class="simple-title--lg">Transações</h1>
	</header>

	<div class="tabs">
		{#each tabs as tab}
			<button
				class="tab {activeTab === tab ? 'active' : ''}"
				on:click={() => switchTab(tab)}
			>
				{tab}
			</button>
		{/each}
	</div>

	<div class="transactions-content">
        {#each transactions as transaction}
            <div class="transactions-content__item">
                <div class="transactions-content__details">
                    <p class="transactions-content__name">{transaction.name}</p>
                    <p class="transactions-content__date">{transaction.date}</p>
                </div>
                <p class="transactions-content__amount">
                    R$ {transaction.amount.toFixed(2)}
                </p>
            </div>
        {/each}
    </div>    
</div>

<style lang="postcss">
	.page {
		@apply flex flex-col min-h-screen items-center gap-6 font-body;

		header {
			@apply w-full flex justify-between items-center mt-4;
		}

		.tabs {
			@apply flex justify-between gap-4 mb-6 w-full max-w-md;

			.tab {
				@apply flex-1 px-6 py-3 rounded-full bg-secondary text-secondary-50 text-sm font-semibold text-center shadow-md transition duration-speed;
				&.active {
					@apply bg-primary text-light shadow-lg;
				}
			}
		}

		.transactions-content {
            @apply flex flex-col gap-4 w-full max-w-md;

            &__item {
                @apply flex justify-between items-center bg-light rounded-lg shadow-sm p-4;
            }

            &__details {
                @apply flex flex-col gap-1;
            }

            &__name {
                @apply text-sm font-medium text-contrast;
            }

            &__date {
                @apply text-xs text-placeholder;
            }

            &__amount {
                @apply text-sm font-bold text-primary;
            }
        }

	}
</style>
