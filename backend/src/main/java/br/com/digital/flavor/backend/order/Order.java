package br.com.digital.flavor.backend.order;

import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "date_finished")
    private LocalDateTime dateFinished;

    @Column(name = "date_cancelled")
    private LocalDateTime dateCancelled;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Canteen canteen;
}
