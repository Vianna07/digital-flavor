import { writable } from 'svelte/store';

let initial = new Map();

if (typeof window !== 'undefined') {
  const stored = localStorage.getItem('order-products');
  initial = stored ? new Map(JSON.parse(stored)) : new Map();
}

export const orderItems = writable<Map<string, { name: string; price: number; quantity: number }>>(initial);
export const orderQuantityTotal = writable(0);

function calculateTotalQuantity() {
  if (typeof window !== 'undefined') {
    const stored = JSON.parse(localStorage.getItem('order-products') as string) || new Map()
    let total = 0;

    for (const [, value] of stored) {
      total += value.quantity;
    }

    orderQuantityTotal.set(total);
  }
}

orderItems.subscribe((value) => {
  if (typeof window !== 'undefined') {
    const sanitizedOrder = new Map(
      Array.from(value.entries())
        .filter(([, item]) => item.quantity > 0)
        .map(([key, item]) => [
          key,
          { ...item, quantity: Math.min(item.quantity, 99) },
        ])
    );

    localStorage.setItem('order-products', JSON.stringify([...sanitizedOrder.entries()]));
    calculateTotalQuantity();
  }
});

