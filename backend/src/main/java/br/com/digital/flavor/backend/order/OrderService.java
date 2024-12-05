package br.com.digital.flavor.backend.order;

import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.canteen.CanteenService;
import br.com.digital.flavor.backend.order_item.OrderItem;
import br.com.digital.flavor.backend.order_item.dto.OrderItemDto;
import br.com.digital.flavor.backend.order_item.OrderItemRepository;
import br.com.digital.flavor.backend.payment.Payment;
import br.com.digital.flavor.backend.payment.PaymentRepository;
import br.com.digital.flavor.backend.payment.PaymentService;
import br.com.digital.flavor.backend.product.ProductService;
import br.com.digital.flavor.backend.user.User;
import br.com.digital.flavor.backend.user.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final CanteenService canteenService;
    private final ProductService productService;
    private final PaymentRepository paymentRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserService userService, CanteenService canteenService, ProductService productService, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
        this.canteenService = canteenService;
        this.productService = productService;
        this.paymentRepository = paymentRepository;
    }

    public void save(List<OrderItemDto> orderItemsDto, String paymentMethod) {
        if (orderItemsDto.isEmpty()) {
            throw new RuntimeException("Não é possível salvar um pedido sem produtos");
        }

        User user = userService.getCurrentUser();
        Canteen canteen = canteenService.getCurrentCanteen();
        Order order = new Order(user, canteen);
        BigDecimal amount = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto dto : orderItemsDto) {
            amount = amount.add(dto.price().multiply(new BigDecimal(dto.quantity())));
            orderItems.add(new OrderItem(order, this.productService.getById(dto.productId()), dto.quantity()));
        }

        this.orderRepository.save(order);
        this.orderItemRepository.saveAll(orderItems);
        this.paymentRepository.save(new Payment(order, amount, paymentMethod, canteen));
    }
}
