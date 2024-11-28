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
  max?: number;
  min?: number;
  step?: string;
	oninput?: (value: string) => void;
}

export type InputProps = Partial<GenericInputProps>;
