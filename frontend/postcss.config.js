export default {
	plugins: {
		'postcss-import': {},
		'tailwindcss/nesting': {},
		'postcss-advanced-variables': {},
		'postcss-calc': {},
		tailwindcss: {},
		autoprefixer: {},
		...(process.env.NODE_ENV === 'production' ? { cssnano: {} } : {})
	}
};
