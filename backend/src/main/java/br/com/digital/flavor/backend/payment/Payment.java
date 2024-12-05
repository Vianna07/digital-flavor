package br.com.digital.flavor.backend.payment;

import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.canteen.CanteenService;
import br.com.digital.flavor.backend.order.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Canteen canteen;

    public Payment() {
        this.id = UUID.randomUUID();
    }

    public Payment(Order order, BigDecimal amount, String paymentMethod, Canteen canteen) {
        this();

        this.order = order;
        this.amount = amount;
        this.method = PaymentMethod.valueOf(paymentMethod);
        this.canteen = canteen;
    }
}
