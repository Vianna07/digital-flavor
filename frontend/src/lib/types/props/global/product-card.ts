
export interface ProductCardProps {
  name: string;
  description: string;
  price: number;
  imageUrl: string;
  onclick?: () => void;
}
