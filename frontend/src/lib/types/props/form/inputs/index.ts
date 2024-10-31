export interface RightIcon {
	url: string;
	onclick?: () => void;
}

export interface InputProps {
	id: string;
	type: string;
	label: string;
	leftIconUrl?: string;
	rightIcon?: RightIcon;
	pattern?: string;
	required?: boolean;
}
