export interface GenericTextareaProps {
	id: string;
	placeholder: string;
	required?: boolean;
	oninput?: (value: string) => void;
	rows?: number;
  value?: string;
}
