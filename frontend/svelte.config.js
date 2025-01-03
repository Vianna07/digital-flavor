import adapter from '@sveltejs/adapter-auto';
import { vitePreprocess } from '@sveltejs/vite-plugin-svelte';

/** @type {import('@sveltejs/kit').Config} */
const config = {
	// Consult https://svelte.dev/docs/kit/integrations
	// for more information about preprocessors
	preprocess: vitePreprocess(),

	kit: {
		// adapter-auto only supports some environments, see https://svelte.dev/docs/kit/adapter-auto for a list.
		// If your environment is not supported, or you settled on a specific environment, switch out the adapter.
		// See https://svelte.dev/docs/kit/adapters for more information about adapters.
		adapter: adapter(),
		alias: {
			'@icons/*': './src/lib/assets/icons/*',
			'@images/*': './src/lib/assets/images/*',
			'@components/*': './src/lib/components/ui/svelte/*',
			// "@components-css/*": "./src/lib/components/ui/tailwindcss/*",
			'@services/*': './src/routes/api/services/*',
      '@stores/*': './src/stores/*'
		}
	}
};

export default config;
