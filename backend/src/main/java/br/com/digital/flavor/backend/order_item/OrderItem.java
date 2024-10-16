package br.com.digital.flavor.backend.order_item;


import br.com.digital.flavor.backend.order.Order;
import br.com.digital.flavor.backend.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @Column(nullable = false)
    private int quantity = 1;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;
}
