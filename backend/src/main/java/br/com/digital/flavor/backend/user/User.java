package br.com.digital.flavor.backend.user;


import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.user.dto.CustomerDto;
import br.com.digital.flavor.backend.user.dto.NewUserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SqlResultSetMapping(
        name = "CustomerDtoMapping",
        classes = @ConstructorResult(
                targetClass = CustomerDto.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
)

@NamedNativeQuery(
        name = "User.findAllCustomers",
        query = "SELECT u.id, u.name, u.email " +
                "  FROM users_canteens uc " +
                "  JOIN users u " +
                "    ON u.id = uc.user_id " +
                "   AND u.user_type = 'CUSTOMER' " +
                " WHERE uc.canteen_id = ?1 ",
        resultSetMapping = "CustomerDtoMapping"
)

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType = UserType.CUSTOMER;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_canteens",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "canteen_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Canteen> canteens = new HashSet<>();

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(UUID uuid, NewUserDto newUserDto, PasswordEncoder passwordEncoder) {
        this.id = uuid;
        this.name = newUserDto.name();
        this.email = newUserDto.email();
        this.password = passwordEncoder.encode(newUserDto.password());

        if (newUserDto.userType() != null) {
            this.userType = newUserDto.userType();
        }
    }

    public boolean isValidLogin(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
