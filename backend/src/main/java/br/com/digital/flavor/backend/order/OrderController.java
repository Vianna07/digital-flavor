package br.com.digital.flavor.backend.order;

import br.com.digital.flavor.backend.order.dto.OrderRequestDto;
import br.com.digital.flavor.backend.order.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody OrderRequestDto dto) {
        this.orderService.save(dto.orderItemsDto(), dto.paymentMethod());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }
}
