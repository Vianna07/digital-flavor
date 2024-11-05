import type { GenericInputProps } from '$lib/types';

export type InputProps = Partial<GenericInputProps>;

export interface PasswordInputProps extends InputProps {
	hasEye?: boolean;
}
