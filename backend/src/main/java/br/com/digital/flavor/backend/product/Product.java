package br.com.digital.flavor.backend.product;


import br.com.digital.flavor.backend.canteen.Canteen;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private int stock;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Canteen canteen;
}