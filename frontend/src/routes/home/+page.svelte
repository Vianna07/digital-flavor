<script lang="ts">
	import type { ProductCardProps } from '$lib/types';
  import ProductCard from '@components/global/ProductCard.svelte';

  const products: ProductCardProps[] = [
    {
        name: 'Café Java',
        description: 'Extra forte',
        price: 1.5,
        imageUrl: 'http://s2.glbimg.com/pGTd3kAbZCgJBM4vEDJmVD806Xo=/e.glbimg.com/og/ed/f/original/2015/03/03/cafezinho.jpg',
    },
    {
        name: 'Chá Verde',
        description: 'Refrescante e saudável',
        price: 2.0,
        imageUrl: 'https://tse2.mm.bing.net/th?id=OIP.nfyh4h0c06uleHuh4hTgqAHaE7&pid=Api&P=0&h=220',
    },
];
</script>

<section class="page">
  <div class="flex gap-8">
    {#each products as product}
        <ProductCard
            {...product}
        />
    {/each}
  </div>
</section>

<style lang="postcss">
	.page {
	}
</style>
