import type { Config } from 'tailwindcss';

import colors from 'tailwindcss/colors';

export default {
	content: ['./src/**/*.{html,js,svelte,ts}'],

	theme: {
		extend: {
			colors: {
				primary: {
					...colors.red,
					DEFAULT: colors.red[600]
				},
				secondary: {
					...colors.gray,
					DEFAULT: colors.gray[600]
				},
				light: colors.gray[100],
				contrast: colors.gray[950],
				placeholder: colors.gray[400]
			},
			fontFamily: {
				body: [
					'Sora',
					'Arial',
					'-apple-system',
					'BlinkMacSystemFont',
					'Segoe UI',
					'Roboto',
					'Oxygen',
					'Ubuntu',
					'Cantarell',
					'Open Sans',
					'Helvetica Neue',
					'sans-serif'
				]
			},
			transitionDuration: {
				speed: '300ms'
			}
		}
	},

	plugins: []
} as Config;
