export interface RightIcon {
	url: string;
	onclick: () => void;
}

export interface BaseInputProps {
	id: string;
	type: string;
	label: string;
	leftIconUrl?: string;
	rightIcon?: RightIcon;
	pattern?: string;
	required?: boolean;
}

export type InputProps = Partial<BaseInputProps>;

export interface PasswordInputProps extends InputProps {
	hasEye?: boolean;
}
