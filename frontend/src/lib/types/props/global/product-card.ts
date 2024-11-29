
export interface ProductCardProps {
  id: string,
  name: string;
  shortDescription: string;
  price: number;
  imageUrl: string;
  onclick?: () => void;
}
