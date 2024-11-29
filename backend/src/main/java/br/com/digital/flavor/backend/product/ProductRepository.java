package br.com.digital.flavor.backend.product;

import br.com.digital.flavor.backend.product.dto.ProductCardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(name = "Product.findAll", nativeQuery = true)
    List<ProductCardDto> findAllByCanteen(UUID canteenId);
}
