package br.com.digital.flavor.backend.product;


import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.product.dto.NewProductDto;
import br.com.digital.flavor.backend.product.dto.ProductCardDto;
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

@SqlResultSetMapping(
        name = "ProductCardDtoMapping",
        classes = @ConstructorResult(
                targetClass = ProductCardDto.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "stock", type = Short.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "short_description", type = String.class),
                        @ColumnResult(name = "image_url", type = String.class),
                }
        )
)

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Product.findAll",
                query = "SELECT id, stock, price, name, short_description, image_url " +
                        "  FROM products " +
                        " WHERE canteen_id = ?1 " +
                        " ORDER BY name ",
                resultSetMapping = "ProductCardDtoMapping"
        ),
        @NamedNativeQuery(
                name = "Product.findAllByName",
                query = "SELECT id, stock, price, name, short_description, image_url " +
                        "  FROM products " +
                        " WHERE canteen_id = ?1 " +
                        "   AND name ILIKE ?2" +
                        " ORDER BY name ",
                resultSetMapping = "ProductCardDtoMapping"
        )
})

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

    @Column(name = "short_description", length = 25)
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

    public Product(NewProductDto dto, Canteen canteen) {
        this.id = UUID.randomUUID();
        this.name = dto.name();
        this.shortDescription = dto.shortDescription();
        this.price = dto.price();
        this.stock = dto.stock();
        this.description = dto.description();
        this.imageUrl = dto.imageUrl();
        this.canteen = canteen;
    }
}