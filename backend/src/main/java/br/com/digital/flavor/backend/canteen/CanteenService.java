package br.com.digital.flavor.backend.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanteenService {

    @Autowired
    private CanteenRepository canteenRepository;

    public List<CanteenDto> getAll() {
        return this.canteenRepository.findAllCanteens();
    }
}
