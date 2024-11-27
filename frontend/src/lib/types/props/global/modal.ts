import type { Snippet } from "svelte";

export interface ModalProps {
  children?: Snippet;
  isOpen: boolean,
  title: string
}
