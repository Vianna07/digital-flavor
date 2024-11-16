package br.com.digital.flavor.backend.user;

import br.com.digital.flavor.backend.user.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query(name = "User.findAllCustomers", nativeQuery = true)
    List<CustomerDto> getAllCustomers(UUID canteenId);
}
