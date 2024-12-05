export enum PaymentMethod {
  PIX = 'PIX',
  CARD = 'CARD',
  CASH = 'CASH',
}

export interface Order {
  id: string;
  name: string;
  price: number;
  quantity: number
}
