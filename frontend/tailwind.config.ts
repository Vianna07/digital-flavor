import type { Config } from 'tailwindcss';

export default {
	content: ['./src/**/*.{html,js,svelte,ts}'],

	theme: {
		extend: {
			colors: {
				primary: '#CA2929',
				secondary: '#E7E7E7',
				'bg-light': '#F9F2ED',
				'bg-dark': '#313131',
				'text-primary': '#000000',
				'text-secondary': '#555555',
				border: 'rgba(0, 0, 0, 0.1)',
				hover: '#E7E7E7',
				'card-bg': '#ffffff',
		  	},
		  	fontFamily: {
				body: [
					'Sora', 'Arial', '-apple-system', 'BlinkMacSystemFont', 
					'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 
					'Open Sans', 'Helvetica Neue', 'sans-serif'
				],
		  	},
		  	spacing: {
				'column-width': '42rem',
				'column-margin-top': '4rem',
		  	},
		  	transitionDuration: {
				'speed': '300ms',
		  	},
		},
	},

	plugins: []
} as Config;
