package br.com.digital.flavor.backend.user;


import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.security.dto.LoginRequest;
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

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType = UserType.CUSTOMER;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_canteens",
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

    public boolean isValidLogin(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}
