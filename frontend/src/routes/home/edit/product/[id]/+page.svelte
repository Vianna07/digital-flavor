<script lang="ts">
	import type { ProductFormProps } from '$lib/types/props/form/product-form';
  import ProductForm from '@components/form/ProductForm.svelte';

  let { data }: { data: { product: ProductFormProps } } = $props();

</script>

<ProductForm {...data.product} submitButtonLabel={"Editar produto"} />
