export * from './inputs/index';

interface SubmitArea {
	text: string;
	paragraph: {
		text: string;
		link: {
			text: string;
			href: string;
		};
	};
}

export interface FormProps {
	title: string;
	submit: SubmitArea;
	inputs: {
		name?: boolean;
		confirmPassword?: boolean;
	};
}
