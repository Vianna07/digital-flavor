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

export interface LoginFormProps {
	title: string;
	submit: SubmitArea;
	inputs?: {
		name?: boolean;
		confirmPassword?: boolean;
	};
}
