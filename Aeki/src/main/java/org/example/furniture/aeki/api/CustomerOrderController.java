package org.example.furniture.aeki.api;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.example.furniture.aeki.api.exceptions.CustomerOrderNotFoundException;
import org.example.furniture.aeki.data.entities.CustomerOrder;
import org.example.furniture.aeki.data.entities.OrderItem;
import org.example.furniture.aeki.data.repositories.CustomerOrderRepository;
import org.example.furniture.aeki.data.repositories.OrderItemRepository;
import org.example.furniture.aeki.model.OrderForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class CustomerOrderController {

    private final CustomerOrderRepository customerOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomOrderControllerDelegate delegate;

    @GetMapping("/orders")
    List<CustomerOrder> all() {
        return customerOrderRepository.findAll();
    }

    @PostMapping("/orders/new")
    @Transactional
    public CustomerOrder newOrder(@RequestParam(name = "customer_id", required = false) Optional<Long> customerId,
                                  @RequestParam(name = "member", required = false) Optional<Boolean> member,
                                  @NotEmpty(message = "empty order")
                                  @RequestBody List<OrderForm> orderForms) {

        CustomerOrder customerOrder = delegate.createOrder(customerId, member);
        customerOrderRepository.save(customerOrder);
        orderForms.forEach(orderForm -> {
            OrderItem orderItem = delegate.createOrderItem(orderForm, customerOrder.getCustomer());
            delegate.saverOrderItem(orderItem, customerOrder);
        });

        return customerOrder;
    }


    @GetMapping("/orders/{id}")
    CustomerOrder one(@PathVariable Long id) {
        return customerOrderRepository.findById(id)
                .orElseThrow(() -> new CustomerOrderNotFoundException(id));
    }
}