package br.com.digital.flavor.backend.canteen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CanteenRepository extends JpaRepository<Canteen, UUID> {
}
