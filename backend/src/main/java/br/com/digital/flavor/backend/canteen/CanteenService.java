package br.com.digital.flavor.backend.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.canteenRepository.findAllByNameOrAddress(nameOrAddress);
    }
}
