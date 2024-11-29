package br.com.digital.flavor.backend.canteen;

import br.com.digital.flavor.backend.security.tenant.CanteenContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CanteenService {

    private final CanteenRepository canteenRepository;

    public CanteenService(CanteenRepository canteenRepository) {
        this.canteenRepository = canteenRepository;
    }

    public List<CanteenDto> getAll() {
        return this.canteenRepository.findAllCanteens();
    }

    public List<CanteenDto> getAllByNameOrAddress(String nameOrAddress) {
        return this.canteenRepository.findAllByNameOrAddress("%" + nameOrAddress + "%");
    }

    public Canteen getCurrentCanteen() {
        Optional<Canteen> canteen = this.canteenRepository.findById(UUID.fromString(CanteenContext.getCurrentCanteenId()));

        if (canteen.isEmpty()) {
            throw new RuntimeException("Cantina não encontrada");
        }

        return canteen.get();
    }

    public CanteenDto getById() {
        return new CanteenDto(this.getCurrentCanteen());
    }
}
