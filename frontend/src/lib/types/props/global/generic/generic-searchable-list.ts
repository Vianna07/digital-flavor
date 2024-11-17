import type { InputProps } from "./generic-input";
import type { GenericListProps } from "./generic-list";

export interface GenericSearchableListProps<T> {
  list: GenericListProps<T>;
  searchInput: InputProps;
  style?: string;
}
