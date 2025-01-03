package br.com.digital.flavor.backend.order;

import br.com.digital.flavor.backend.order.dto.OrderResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(name = "Order.findAll", nativeQuery = true)
    List<OrderResponseDto> findAllByCanteen(UUID canteenId);
}
