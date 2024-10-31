import type { Config } from 'tailwindcss';

export default {
	content: ['./src/**/*.{html,js,svelte,ts}'],

	theme: {
		extend: {
			colors: {
				primary: '#CA2929',
				secondary: '#555555',
				light: '#F7F8F8FA',
				placeholder: '#C1C1C1',
				hover: '#E7E7E7',
				dark: '#313131'
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
