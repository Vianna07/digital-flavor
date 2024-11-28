package br.com.digital.flavor.backend.canteen;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CanteenRepository extends JpaRepository<Canteen, UUID> {

    @Query(name = "Canteen.findAll", nativeQuery = true)
    List<CanteenDto> findAllCanteens();

    @Query(name = "Canteen.findAllByNameOrAddress", nativeQuery = true)
    List<CanteenDto> findAllByNameOrAddress(String nameOrAddress);
}
