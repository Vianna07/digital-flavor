package br.com.digital.flavor.backend.user;

import br.com.digital.flavor.backend.user.dto.CustomerDto;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(name = "User.findAllCustomersByNameOrEmail", nativeQuery = true)
    List<CustomerDto> getAllCustomersByNameOrEmail(UUID canteenId, String nameOrEmail);

    @Modifying
    @Query(value = "INSERT INTO main.users_canteens (user_id, canteen_id) " +
                   "VALUES (:userId, :canteenId) " +
                   "ON CONFLICT DO NOTHING ",
           nativeQuery = true)
    void saveUserInCanteen(@Param("userId") UUID userId, @Param("canteenId") UUID canteenId);
}
