export interface RightIcon {
	url: string;
	onclick: () => void;
}

export interface GenericInputProps {
	id: string;
	type: string;
	label: string;
	leftIconUrl?: string;
	rightIcon?: RightIcon;
	pattern?: string;
	required?: boolean;
	oninput?: () => void;
}

export type InputProps = Partial<GenericInputProps>;

export interface PasswordInputProps extends InputProps {
	hasEye?: boolean;
}
