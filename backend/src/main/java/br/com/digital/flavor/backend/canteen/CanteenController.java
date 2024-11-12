package br.com.digital.flavor.backend.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/canteen")
public class CanteenController {

    private final CanteenService canteenService;

    public CanteenController(CanteenService canteenService) {
        this.canteenService = canteenService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CanteenDto>> getAll() {
        return ResponseEntity.ok(this.canteenService.getAll());
    }

    @GetMapping("/get-all-by-name-or-address/{nameOrAddress}")
    public ResponseEntity<List<CanteenDto>> getAllByNameOrAddress(@PathVariable String nameOrAddress) {
        return ResponseEntity.ok(this.canteenService.getAllByNameOrAddress(nameOrAddress));
    }
}
