package br.com.digital.flavor.backend.order;

import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.order.dto.OrderResponseDto;
import br.com.digital.flavor.backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SqlResultSetMapping(
        name = "OrderDtoMapping",
        classes = @ConstructorResult(
                targetClass = OrderResponseDto.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "user_name", type = String.class),
                        @ColumnResult(name = "value", type = BigDecimal.class),
                        @ColumnResult(name = "created_at", type = String.class)
                }
        )
)

@NamedNativeQuery(
        name = "Order.findAll",
        query = "SELECT o.id, u.\"name\" AS user_name, SUM(oi.quantity * p.price) AS value, TO_CHAR(o.created_at, 'DD/MM/YYYY HH24:MI:SS') AS created_at " +
                "  FROM order_items oi " +
                "  JOIN orders o " +
                "    ON o.id = oi.order_id AND o.canteen_id = ?1 " +
                "  JOIN products p " +
                "    ON p.id = oi.product_id " +
                "  JOIN users u " +
                "    ON u.id = o.user_id " +
                " GROUP BY o.id, u.\"name\", o.created_at " +
                " ORDER BY o.created_at DESC;",
        resultSetMapping = "OrderDtoMapping"
)

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "date_finished")
    private LocalDateTime dateFinished;

    @Column(name = "date_cancelled")
    private LocalDateTime dateCancelled;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "canteen_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Canteen canteen;

    public Order() {
        this.id = UUID.randomUUID();
    }

    public Order(User user, Canteen canteen) {
        this();

        this.dateFinished =  LocalDateTime.now();
        this.user = user;
        this.status = OrderStatus.FINALIZED;
        this.canteen = canteen;
    }
}
