package br.com.digital.flavor.backend.order;

import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
}
