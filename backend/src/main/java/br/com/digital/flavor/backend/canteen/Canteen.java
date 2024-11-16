package br.com.digital.flavor.backend.canteen;


import br.com.digital.flavor.backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SqlResultSetMapping(
        name = "CanteenDtoMapping",
        classes = @ConstructorResult(
                targetClass = CanteenDto.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "address", type = String.class)
                }
        )
)



@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Canteen.findAll",
                query = "SELECT id, name, address FROM canteens",
                resultSetMapping = "CanteenDtoMapping"
        ),
        @NamedNativeQuery(
                name = "Canteen.findAllByNameOrAddress",
                query = "SELECT id, name, address FROM canteens WHERE name ILIKE ?1 OR address ILIKE ?1",
                resultSetMapping = "CanteenDtoMapping"
        )
})

@Data
@NoArgsConstructor
@Entity
@Table(name = "canteens")
public class Canteen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User manager;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 150)
    private String address;

    @ManyToMany(mappedBy = "canteens")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();
}