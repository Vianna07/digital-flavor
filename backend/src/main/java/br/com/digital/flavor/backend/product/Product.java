package br.com.digital.flavor.backend.product;


import br.com.digital.flavor.backend.canteen.Canteen;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Short stock;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal price;

    @Column(precision = 7, scale = 2)
    private BigDecimal cost;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Canteen canteen;

    public Product(NewProductDto dto) {
        this.id = UUID.randomUUID();
        this.name = dto.name();
        this.shortDescription = dto.shortDescription();
        this.price = dto.price();
        this.stock = dto.stock();
        this.description = dto.description();
        this.imageUrl = dto.imageUrl();
    }
}