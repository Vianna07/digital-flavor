package br.com.digital.flavor.backend.order;

import br.com.digital.flavor.backend.order_item.dto.OrderItemDto;
import br.com.digital.flavor.backend.order_item.dto.OrderRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
